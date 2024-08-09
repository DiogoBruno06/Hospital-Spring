package com.cadastro.hospital.entity;

import com.cadastro.hospital.enums.Sexo;
import com.cadastro.hospital.enums.TipoFuncionario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "FUNCIONARIOENTITY")
public class FuncionarioEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FUNCIONARIO_SEQUENCE")
    @SequenceGenerator(name = "FUNCIONARIO_SEQUENCE", sequenceName = "seq_funcionario", allocationSize = 1)
    @Column(name = "ID_FUNCIONARIO")
    private Integer idFuncionario;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DATADENASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "SEXO")
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Column(name = "EMAIL")
    private String email;


    @Column(name = "LOGIN")
    private String login;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "TIPO_FUNCIONARIO")
    private TipoFuncionario tipoFuncionario;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "FUNCIONARIO_CARGO",
            joinColumns = @JoinColumn(name = "ID_FUNCIONARIO"),
            inverseJoinColumns = @JoinColumn(name = "ID_CARGO")
    )
    private Set<CargoEntity> cargos = new HashSet<>();

    public void addCargo(CargoEntity cargoEntity) {
        this.cargos.add(cargoEntity);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
