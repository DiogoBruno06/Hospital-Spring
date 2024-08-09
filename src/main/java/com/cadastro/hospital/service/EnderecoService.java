package com.cadastro.hospital.service;


import com.cadastro.hospital.dto.Endereco.EnderecoCreateDTO;
import com.cadastro.hospital.dto.Endereco.EnderecoDTO;
import com.cadastro.hospital.entity.EnderecoEntity;
import com.cadastro.hospital.entity.FuncionarioEntity;
import com.cadastro.hospital.exception.EntidadeNaoEncontradaException;
import com.cadastro.hospital.repository.EnderecoRepository;
import com.cadastro.hospital.repository.FuncionarioRepository;
import com.cadastro.hospital.util.ConversorMapper;
import com.cadastro.hospital.util.ValidarUF;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public List<EnderecoDTO> findAll() {
        return enderecoRepository.findAll().stream()
                .map(enderecoEntity -> ConversorMapper.converter(enderecoEntity, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public EnderecoDTO findById(Integer id) throws EntidadeNaoEncontradaException {
        Optional<EnderecoEntity> enderecoExisteOP = enderecoRepository.findById(id);

        if (enderecoExisteOP.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Endereço não existe");
        }
        return ConversorMapper.converter(enderecoExisteOP.get(), EnderecoDTO.class);
    }

    public EnderecoDTO save(Integer idCliente, EnderecoCreateDTO endereco) throws EntidadeNaoEncontradaException {
        Optional<FuncionarioEntity> funcionarioExisteOp = funcionarioRepository.findById(idCliente);

        if (funcionarioExisteOp.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Funcionario não existe");
        }


        EnderecoEntity enderecoEntity = ConversorMapper.converter(endereco, EnderecoEntity.class);

        if (!ValidarUF.estadoValido(enderecoEntity.getUf())) {
            throw new EntidadeNaoEncontradaException("UF INVÁLIDA");
        }
        enderecoEntity.setFuncionarioEndereco(funcionarioExisteOp.get());

        enderecoRepository.save(enderecoEntity);

        return ConversorMapper.converter(enderecoEntity, EnderecoDTO.class);

    }

    public EnderecoDTO update(Integer id, EnderecoCreateDTO enderecoDTO) throws EntidadeNaoEncontradaException {
        Optional<EnderecoEntity> enderecoEntityRecuperadoOp = enderecoRepository.findById(id);
        if (enderecoEntityRecuperadoOp.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Endereco não existe");
        }
        EnderecoEntity enderecoEntityRecuperado = enderecoEntityRecuperadoOp.get();

        enderecoEntityRecuperado.setApelido(enderecoDTO.getApelido());
        enderecoEntityRecuperado.setLogradouro(enderecoDTO.getLogradouro());
        enderecoEntityRecuperado.setNumero(enderecoDTO.getNumero());
        enderecoEntityRecuperado.setComplemento(enderecoDTO.getComplemento());
        enderecoEntityRecuperado.setCep(enderecoDTO.getCep());
        enderecoEntityRecuperado.setBairro(enderecoDTO.getBairro());
        enderecoEntityRecuperado.setCidade(enderecoDTO.getCidade());
        enderecoEntityRecuperado.setUf(enderecoDTO.getUf());

        enderecoRepository.save(enderecoEntityRecuperado);

        return ConversorMapper.converter(enderecoEntityRecuperado, EnderecoDTO.class);

    }

    public void delete(Integer idEndereco) throws EntidadeNaoEncontradaException {
        if (!enderecoRepository.existsById(idEndereco)) {
            throw new EntidadeNaoEncontradaException("Endereço não encontrado com ID: " + idEndereco);
        }
        enderecoRepository.deleteById(idEndereco);
    }


}
