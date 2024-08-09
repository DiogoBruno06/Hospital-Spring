package com.cadastro.hospital.service;

import com.cadastro.hospital.dto.Consulta.ConsultaCreateDTO;
import com.cadastro.hospital.dto.Consulta.ConsultaDTO;
import com.cadastro.hospital.dto.Farmacia.FarmaciaDTO;
import com.cadastro.hospital.entity.ConsultaEntity;
import com.cadastro.hospital.entity.FuncionarioEntity;
import com.cadastro.hospital.entity.PacienteEntity;
import com.cadastro.hospital.exception.EntidadeNaoEncontradaException;
import com.cadastro.hospital.repository.ConsultaRepository;
import com.cadastro.hospital.repository.FuncionarioRepository;
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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ObjectMapper objectMapper;

    public List<ConsultaEntity> findAll(){
        return consultaRepository.findAll();
    }

    public ConsultaDTO create(ConsultaCreateDTO consulta, Integer idPaciente) throws EntidadeNaoEncontradaException {
        Optional<PacienteEntity> pacienteOptional = pacienteRepository.findById(idPaciente);
        Optional<FuncionarioEntity> funcionarioOptional = funcionarioRepository.findById(getIdLoggedUser());

        if (pacienteOptional.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Paciente não encontrado");
        }

        if (funcionarioOptional.isEmpty()){
            throw new EntidadeNaoEncontradaException("Funcionario nao encontrado");
        }

        ConsultaEntity consultaEntity = ConversorMapper.converter(consulta, ConsultaEntity.class);
        consultaEntity.setPacienteConsulta(pacienteOptional.get());
        consultaEntity.setFuncionarioConsulta(funcionarioOptional.get());

        ConsultaEntity consultaEntityCriada = consultaRepository.save(consultaEntity);

        return convertToDTO(consultaEntityCriada);
    }

    public ConsultaDTO update(Integer idConsulta, Integer idPaciente, ConsultaCreateDTO consultaCreateDTO) throws EntidadeNaoEncontradaException {
        Optional<PacienteEntity> pacienteOptional = pacienteRepository.findById(idPaciente);
        Optional<FuncionarioEntity> funcionarioOptional = funcionarioRepository.findById(getIdLoggedUser());
        Optional<ConsultaEntity> consultaEntityRecuperadaOp = consultaRepository.findById(idConsulta);

        if (pacienteOptional.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Paciente não existe");
        }

        if (funcionarioOptional.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Funcionario não existe");
        }

        if (consultaEntityRecuperadaOp.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Consulta não existe");
        }

        ConsultaEntity consultaEntityConvertido = objectMapper.convertValue(consultaCreateDTO, ConsultaEntity.class);

        consultaEntityConvertido.setDataConsulta(consultaCreateDTO.getDataConsulta());
        consultaEntityConvertido.setSintomas(consultaCreateDTO.getSintomas());
        consultaEntityConvertido.setFuncionarioConsulta(funcionarioOptional.get());
        consultaEntityConvertido.setPacienteConsulta(pacienteOptional.get());


        ConsultaEntity consultaEntityCriado = consultaRepository.save(consultaEntityConvertido);

        return convertToDTO(consultaEntityCriado);

    }

    public Page<ConsultaDTO> findAllPaginado(int pagina, int quantidadeRegistros){
        Sort ordenacao = Sort.by("idConsulta");
        Pageable pageable = PageRequest.of(pagina, quantidadeRegistros, ordenacao);
        return consultaRepository.findAll(pageable).map(ConversorMapper::convertConsultaToOutput);
    }

    public Integer getIdLoggedUser() {
        return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }
    private ConsultaDTO convertToDTO(ConsultaEntity consultaEntity) {
        return objectMapper.convertValue(consultaEntity, ConsultaDTO.class);
    }
}
