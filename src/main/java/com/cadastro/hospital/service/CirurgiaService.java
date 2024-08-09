package com.cadastro.hospital.service;

import com.cadastro.hospital.dto.Cirurgia.CirurgiaCreateDTO;
import com.cadastro.hospital.dto.Cirurgia.CirurgiaDTO;
import com.cadastro.hospital.dto.Cirurgia.CirurgiaQueryDTO;
import com.cadastro.hospital.dto.Funcionario.FuncionarioCirurgiaQueryDTO;
import com.cadastro.hospital.dto.Paciente.PacienteCirurgiaQueryDTO;
import com.cadastro.hospital.entity.*;
import com.cadastro.hospital.exception.EntidadeNaoEncontradaException;
import com.cadastro.hospital.exception.RegraDeNegocioException;
import com.cadastro.hospital.repository.CirurgiaRepository;
import com.cadastro.hospital.repository.FuncionarioRepository;
import com.cadastro.hospital.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CirurgiaService {
    private final CirurgiaRepository cirurgiaRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final PacienteRepository pacienteRepository;
    private final ObjectMapper objectMapper;


    public CirurgiaDTO create2(CirurgiaCreateDTO cirurgiaCreateDTO, Integer idPaciente) throws EntidadeNaoEncontradaException {
        CirurgiaEntity cirurgia = objectMapper.convertValue(cirurgiaCreateDTO, CirurgiaEntity.class);

        Optional<PacienteEntity> pacienteOptional = pacienteRepository.findById(idPaciente);
        if (pacienteOptional.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Paciente não encontrado");
        }
        PacienteEntity paciente = pacienteOptional.get();

        Set<FuncionarioEntity> funcionarios = new HashSet<>();

        for (Integer funcionarioId : cirurgiaCreateDTO.getFuncionariosCirurgia()) {
            Optional<FuncionarioEntity> funcionarioOptional = funcionarioRepository.findById(funcionarioId);
            funcionarioOptional.ifPresent(funcionarios::add);
        }

        cirurgia.setCirurgiaFuncionarios(funcionarios);
        cirurgia.setCirurgiaPaciente(paciente);

        CirurgiaEntity cirurgiaSalva = cirurgiaRepository.save(cirurgia);
        return objectMapper.convertValue(cirurgiaSalva, CirurgiaDTO.class);
    }

    public CirurgiaQueryDTO getCirurgiaDetailsById(Integer cirurgiaId) {
        Optional<CirurgiaEntity> cirurgiaOptional = cirurgiaRepository.findById(cirurgiaId);
        if (cirurgiaOptional.isPresent()) {
            CirurgiaEntity cirurgia = cirurgiaOptional.get();
            CirurgiaQueryDTO cirurgiaDTO = new CirurgiaQueryDTO();
            cirurgiaDTO.setIdCirurgia(cirurgia.getIdCirurgia());
            cirurgiaDTO.setDataCirurgia(cirurgia.getDataCirurgia());
            cirurgiaDTO.setTipoCirurgia(cirurgia.getTipoCirurgia());

            List<FuncionarioCirurgiaQueryDTO> funcionariosDTO = new ArrayList<>();
            for (FuncionarioEntity funcionario : cirurgia.getCirurgiaFuncionarios()) {
                FuncionarioCirurgiaQueryDTO funcionarioDTO = new FuncionarioCirurgiaQueryDTO();
                funcionarioDTO.setIdFuncionario(funcionario.getIdFuncionario());
                funcionarioDTO.setNome(funcionario.getNome());
                funcionarioDTO.setEmail(funcionario.getEmail());
                funcionarioDTO.setTipoFuncionario(funcionario.getTipoFuncionario());
                funcionariosDTO.add(funcionarioDTO);
            }
            cirurgiaDTO.setFuncionarios(funcionariosDTO);

            PacienteEntity paciente = cirurgia.getCirurgiaPaciente();
            if (paciente != null) {
                PacienteCirurgiaQueryDTO pacienteDTO = new PacienteCirurgiaQueryDTO();
                pacienteDTO.setIdPaciente(paciente.getIdPaciente());
                pacienteDTO.setNome(paciente.getNome());
                pacienteDTO.setSobrenome(paciente.getSobrenome());
                pacienteDTO.setEmail(paciente.getEmail());
                cirurgiaDTO.setPaciente(pacienteDTO);
            }

            return cirurgiaDTO;
        } else {
            return null;
        }
    }


    public CirurgiaEntity findById(Integer idCirurgia) throws RegraDeNegocioException {
        return cirurgiaRepository.findById(idCirurgia)
                .orElseThrow(() ->
                        new RegraDeNegocioException("Cirurgia não encontrado!"));
    }
}
