package com.cadastro.hospital.security;

import com.cadastro.hospital.entity.FuncionarioEntity;
import com.cadastro.hospital.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final FuncionarioService funcionarioService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<FuncionarioEntity> funcionarioEntityOptional = funcionarioService.findByLogin(username);

        return funcionarioEntityOptional
                .orElseThrow(() -> new UsernameNotFoundException("Usuario inválido"));
    }

}
