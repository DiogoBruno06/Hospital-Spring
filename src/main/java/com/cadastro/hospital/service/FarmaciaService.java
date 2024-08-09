package com.cadastro.hospital.service;

import com.cadastro.hospital.dto.Farmacia.FarmaciaCreateDTO;
import com.cadastro.hospital.dto.Farmacia.FarmaciaDTO;
import com.cadastro.hospital.entity.FarmaciaEntity;
import com.cadastro.hospital.entity.FuncionarioEntity;
import com.cadastro.hospital.entity.RemedioSaidaEntity;
import com.cadastro.hospital.enums.StatusFarmacia;
import com.cadastro.hospital.enums.TipoRemedio;
import com.cadastro.hospital.exception.EntidadeNaoEncontradaException;
import com.cadastro.hospital.exception.RegraDeNegocioException;
import com.cadastro.hospital.repository.FarmaciaRepository;
import com.cadastro.hospital.repository.FuncionarioRepository;
import com.cadastro.hospital.repository.RemedioSaidaRepository;
import com.cadastro.hospital.util.ConversorMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.management.StringValueExp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FarmaciaService {
    private final FarmaciaRepository farmaciaRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final RemedioSaidaRepository remedioSaidaRepository;
    private final ObjectMapper objectMapper;

    public FarmaciaDTO create(FarmaciaCreateDTO farmacia) throws EntidadeNaoEncontradaException {
        Optional<FuncionarioEntity> funcionarioOptional = funcionarioRepository.findById(getIdLoggedUser());

        if (funcionarioOptional.isEmpty()){
            throw new EntidadeNaoEncontradaException("Funcionario nao encontrado");
        }

        FarmaciaEntity farmaciaEntity = ConversorMapper.converter(farmacia, FarmaciaEntity.class);


        if (farmacia.getTipoRemedio() == TipoRemedio.COMPRIMIDO) {
            farmaciaEntity.setGramatura(farmacia.getGramatura() + " " + "MG");
        } else {
            farmaciaEntity.setGramatura(farmacia.getGramatura() + " " + "ML");
        }

        farmaciaEntity.setFuncionarioFarmacia(funcionarioOptional.get());
        farmaciaEntity.setDataPedido(LocalDate.now());
        farmaciaEntity.setStatusFarmacia(StatusFarmacia.PRODUCAO);


        FarmaciaEntity farmaciaEntityCriado = farmaciaRepository.save(farmaciaEntity);
        return convertToDTO(farmaciaEntityCriado);
    }

    public FarmaciaDTO entregaRemedio(Integer idFarmacia) throws EntidadeNaoEncontradaException {
        Optional<FarmaciaEntity> farmaciaEntityOptional = farmaciaRepository.findById(idFarmacia);
        if (farmaciaEntityOptional.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Pedido da farmacia nao encontrado, Digite um valido");
        }
        FarmaciaEntity farmaciaEntity = farmaciaEntityOptional.get();
        farmaciaEntity.setDataEntrega(LocalDate.now());
        farmaciaEntity.setStatusFarmacia(StatusFarmacia.ENTREGUE);
        FarmaciaEntity farmaciaEntityCriado = farmaciaRepository.save(farmaciaEntity);
        return convertToDTO(farmaciaEntityCriado);
    }

    public FarmaciaDTO retiradaRemedio(Integer idFarmacia, Integer quantidade) throws Exception {
        Optional<FarmaciaEntity> farmaciaEntityOptional = farmaciaRepository.findById(idFarmacia);
        Optional<FuncionarioEntity> funcionarioEntityOptional = funcionarioRepository.findById(getIdLoggedUser());

        RemedioSaidaEntity remedioSaidaEntity = new RemedioSaidaEntity();
        if (farmaciaEntityOptional.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Pedido da farmacia nao encontrado, Digite um valido");
        }
        FarmaciaEntity farmaciaEntity = farmaciaEntityOptional.get();

        if (farmaciaEntity.getQuantidade() < quantidade || !Objects.equals(farmaciaEntity.getStatusFarmacia().toString(), "ENTREGUE")) {
            throw new Exception("Quantidade pedida não é suficiente ou pedido não entregue");
        }
        farmaciaEntity.setQuantidade(farmaciaEntity.getQuantidade() - quantidade);

        remedioSaidaEntity.setIdRemedio(farmaciaEntity.getIdFarmacia());
        remedioSaidaEntity.setNomeRemedio(farmaciaEntity.getNomeRemedio());
        remedioSaidaEntity.setQuantidade(quantidade);
        remedioSaidaEntity.setFuncionarioRemedio(funcionarioEntityOptional.get());
        remedioSaidaEntity.setTipoRemedio(farmaciaEntity.getTipoRemedio());
        remedioSaidaEntity.setSaidaRemedio(Date.from(Instant.now()));

        FarmaciaEntity farmaciaEntityCriado = farmaciaRepository.save(farmaciaEntity);
        RemedioSaidaEntity remedioSaidaCriado = remedioSaidaRepository.save(remedioSaidaEntity);
        return convertToDTO(farmaciaEntityCriado);
    }

    public FarmaciaEntity findById(Integer idFarmacia) throws RegraDeNegocioException {
        return farmaciaRepository.findById(idFarmacia)
                .orElseThrow(() ->
                        new RegraDeNegocioException("Usuário não encontrado!"));
    }

    public List<FarmaciaEntity> queryInjetavel(){
        return farmaciaRepository.findByTipoRemedio(TipoRemedio.INJETAVEL);
    }

    public List<FarmaciaEntity> queryComprimido(){
        return farmaciaRepository.findByTipoRemedio(TipoRemedio.COMPRIMIDO);
    }

    public List<FarmaciaEntity> queryLiquido(){
        return farmaciaRepository.findByTipoRemedio(TipoRemedio.LIQUIDO);
    }

    public Integer getIdLoggedUser() {
        return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }
    private FarmaciaDTO convertToDTO(FarmaciaEntity farmaciaEntity) {
        return objectMapper.convertValue(farmaciaEntity, FarmaciaDTO.class);
    }

    public Page<FarmaciaDTO> findAllPaginado(int pagina, int quantidadeRegistros){
        Sort ordenacao = Sort.by("idFarmacia");
        Pageable pageable = PageRequest.of(pagina, quantidadeRegistros, ordenacao);
        return farmaciaRepository.findAll(pageable).map(ConversorMapper::convertFarmaciaToOutput);
    }
}
