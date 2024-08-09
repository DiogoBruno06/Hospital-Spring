package com.cadastro.hospital.service;

import com.cadastro.hospital.dto.Farmacia.FarmaciaDTO;
import com.cadastro.hospital.dto.Funcionario.FuncionarioCreateDTO;
import com.cadastro.hospital.dto.Funcionario.FuncionarioDTO;
import com.cadastro.hospital.dto.Funcionario.FuncionarioUpdateDTO;
import com.cadastro.hospital.entity.CargoEntity;
import com.cadastro.hospital.entity.FuncionarioEntity;
import com.cadastro.hospital.exception.RegraDeNegocioException;
import com.cadastro.hospital.repository.FuncionarioRepository;
import com.cadastro.hospital.util.ConversorMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Data
public class FuncionarioService {
    private final CargoService cargoService;
    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    @Transactional
    public FuncionarioDTO create(FuncionarioCreateDTO funcionario) throws RegraDeNegocioException {
        CargoEntity cargoEntity = cargoService.getByid(2);

        FuncionarioEntity funcionarioEntityConvertido = objectMapper.convertValue(funcionario, FuncionarioEntity.class);

        funcionarioEntityConvertido.addCargo(cargoEntity);
        funcionarioEntityConvertido.setSenha(passwordEncoder.encode(funcionarioEntityConvertido.getSenha()));
        funcionarioEntityConvertido.setLogin(funcionario.getEmail());

        FuncionarioEntity funcionarioEntityCriado = funcionarioRepository.save(funcionarioEntityConvertido);

        return convertToDTO(funcionarioEntityCriado);
    }

    public FuncionarioDTO update(FuncionarioUpdateDTO funcionarioUpdateDTO)  throws Exception{

        FuncionarioEntity funcionarioEntity = funcionarioRepository.findById(getIdLoggedUser())
                .orElseThrow(()-> new RegraDeNegocioException("Funcionario não encontrado"));

        funcionarioEntity.setNome(funcionarioUpdateDTO.getNome());
        funcionarioEntity.setDataNascimento(funcionarioUpdateDTO.getDataNascimento());
        funcionarioEntity.setCpf(funcionarioUpdateDTO.getCpf());

        return convertToDTO(funcionarioRepository.save(funcionarioEntity));

    }

    public Optional<FuncionarioEntity> findByLogin(String login) {
        return funcionarioRepository.findByLogin(login);
    }

    public Page<FuncionarioDTO> findAllPaginado(int pagina, int quantidadeRegistros){
        Sort ordenacao = Sort.by("idFuncionario");
        Pageable pageable = PageRequest.of(pagina, quantidadeRegistros, ordenacao);
        return funcionarioRepository.findAll(pageable).map(ConversorMapper::convertFuncionarioToOutput);
    }

    public Integer getIdLoggedUser() {
        return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }
    private FuncionarioDTO convertToDTO(FuncionarioEntity funcionarioEntity) {
        return objectMapper.convertValue(funcionarioEntity, FuncionarioDTO.class);
    }


}
