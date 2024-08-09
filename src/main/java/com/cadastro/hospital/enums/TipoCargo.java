package com.cadastro.hospital.enums;

public enum TipoCargo {
    ROLE_ADMIN(1),
    ROLE_FUNCIONARIO(2),

    ROLE_PACIENTE(3);
    private Integer tipo;

    TipoCargo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getTipo() {
        return tipo;
    }

}
