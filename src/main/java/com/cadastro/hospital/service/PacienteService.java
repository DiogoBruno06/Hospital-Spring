package com.cadastro.hospital.service;

import com.cadastro.hospital.dto.Farmacia.FarmaciaDTO;
import com.cadastro.hospital.dto.Paciente.PacienteCreateDTO;
import com.cadastro.hospital.dto.Paciente.PacienteDTO;
import com.cadastro.hospital.entity.CargoEntity;
import com.cadastro.hospital.entity.PacienteEntity;
import com.cadastro.hospital.enums.StatusPaciente;
import com.cadastro.hospital.exception.RegraDeNegocioException;
import com.cadastro.hospital.repository.PacienteRepository;
import com.cadastro.hospital.util.ConversorMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final ObjectMapper objectMapper;
    private final CargoService cargoService;

    public PacienteDTO create(PacienteCreateDTO paciente) throws RegraDeNegocioException {
        if (pacienteRepository.existsByCpf(paciente.getCpf())) {
            throw new RegraDeNegocioException("Já existe um paciente com o mesmo CPF.");
        }
        CargoEntity cargoEntity = cargoService.getByid(3);

        PacienteEntity pacienteEntityConvertido = objectMapper.convertValue(paciente, PacienteEntity.class);

        pacienteEntityConvertido.addCargo(cargoEntity);
        if (Objects.equals(paciente.getEntradaConsulta().toString(), "LARANJA")){
            pacienteEntityConvertido.setStatusPaciente(StatusPaciente.ATENDIMENTO);
        }
        else if (Objects.equals(paciente.getEntradaConsulta().toString(), "VERMELHO")){
            pacienteEntityConvertido.setStatusPaciente(StatusPaciente.ATENDIMENTO);
        } else {
            pacienteEntityConvertido.setStatusPaciente(StatusPaciente.ESPERA);
        }
        PacienteEntity pacienteEntityCriado = pacienteRepository.save(pacienteEntityConvertido);

        return convertToDTO(pacienteEntityCriado);
    }

    public PacienteEntity findById(Integer idPaciente) throws RegraDeNegocioException {
        return pacienteRepository.findById(idPaciente)
                .orElseThrow(() ->
                        new RegraDeNegocioException("Paciente não encontrado!"));
    }

    public PacienteDTO altaPaciente(Integer idPaciente) throws RegraDeNegocioException {
        PacienteEntity pacienteEntity = pacienteRepository.findById(idPaciente)
                .orElseThrow(()-> new RegraDeNegocioException("Paciente não encontrado"));

        if (Objects.equals(pacienteEntity.getStatusPaciente().toString(), "ESPERA") ||
                Objects.equals(pacienteEntity.getStatusPaciente().toString(), "REPOUSO")) {
            throw new RegraDeNegocioException("O Status só pode ser atualizado se o Paciente for atendido");
        } else {
            pacienteEntity.setStatusPaciente(StatusPaciente.ALTA);
        }
        PacienteEntity pacienteEntityCriado = pacienteRepository.save(pacienteEntity);

        return convertToDTO(pacienteEntityCriado);
    }

    public PacienteDTO atendimentoPaciente(Integer idPaciente) throws RegraDeNegocioException {
        PacienteEntity pacienteEntity = pacienteRepository.findById(idPaciente)
                .orElseThrow(()-> new RegraDeNegocioException("Paciente não encontrado"));

        if (Objects.equals(pacienteEntity.getStatusPaciente().toString(), "ATENDIMENTO") ||
                Objects.equals(pacienteEntity.getStatusPaciente().toString(), "REPOUSO")||
                    Objects.equals(pacienteEntity.getStatusPaciente().toString(), "ALTA")){
            throw new RegraDeNegocioException("O paciente deve ser atendido se estiver em ESPERA");
        } else {
            pacienteEntity.setStatusPaciente(StatusPaciente.ATENDIMENTO);
        }
        PacienteEntity pacienteEntityCriado = pacienteRepository.save(pacienteEntity);

        return convertToDTO(pacienteEntityCriado);
    }

    public Page<PacienteDTO> findAllPaginado(int pagina, int quantidadeRegistros){
        Sort ordenacao = Sort.by("idPaciente");
        Pageable pageable = PageRequest.of(pagina, quantidadeRegistros, ordenacao);
        return pacienteRepository.findAll(pageable).map(ConversorMapper::convertPacienteToOutput);
    }

    private PacienteDTO convertToDTO(PacienteEntity pacienteEntity) {
        return objectMapper.convertValue(pacienteEntity, PacienteDTO.class);
    }
}
