package com.cadastro.hospital.util;

import com.cadastro.hospital.dto.Consulta.ConsultaDTO;
import com.cadastro.hospital.dto.Farmacia.FarmaciaDTO;
import com.cadastro.hospital.dto.Funcionario.FuncionarioDTO;
import com.cadastro.hospital.dto.Paciente.PacienteDTO;
import com.cadastro.hospital.entity.ConsultaEntity;
import com.cadastro.hospital.entity.FarmaciaEntity;
import com.cadastro.hospital.entity.FuncionarioEntity;
import com.cadastro.hospital.entity.PacienteEntity;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class ConversorMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @SneakyThrows
    public static <TipoEntrada, TipoSaida> TipoSaida converter(TipoEntrada tipoConvertido, Class<TipoSaida> toValueType) {
        return objectMapper.convertValue(tipoConvertido, toValueType);
    }

    public static FarmaciaDTO convertFarmaciaToOutput(FarmaciaEntity farmaciaEntity){
        FarmaciaDTO farmaciaDTO =
                ConversorMapper.converter(farmaciaEntity, FarmaciaDTO.class);
        farmaciaDTO.setIdFarmacia(farmaciaEntity.getIdFarmacia());
        return farmaciaDTO;
    }

    public static PacienteDTO convertPacienteToOutput(PacienteEntity pacienteEntity){
        PacienteDTO pacienteDTO =
                ConversorMapper.converter(pacienteEntity, PacienteDTO.class);
        pacienteDTO.setIdPaciente(pacienteEntity.getIdPaciente());
        return pacienteDTO;
    }

    public static FuncionarioDTO convertFuncionarioToOutput(FuncionarioEntity funcionarioEntity){
        FuncionarioDTO funcionarioDTO =
                ConversorMapper.converter(funcionarioEntity, FuncionarioDTO.class);
        funcionarioDTO.setIdFuncionario(funcionarioDTO.getIdFuncionario());
        return funcionarioDTO;
    }

    public static ConsultaDTO convertConsultaToOutput(ConsultaEntity consultaEntity){
        ConsultaDTO consultaDTO =
                ConversorMapper.converter(consultaEntity, ConsultaDTO.class);
        consultaDTO.setIdConsulta(consultaDTO.getIdConsulta());
        return consultaDTO;
    }



}
