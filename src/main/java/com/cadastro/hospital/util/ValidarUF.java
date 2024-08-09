package com.cadastro.hospital.util;

public class ValidarUF {
    public static boolean estadoValido(String uf) {
        if (uf == null || uf.isEmpty() || uf.length() != 2) {
            return false;
        }

        String[] ufsValidas = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};

        for (String ufValida : ufsValidas) {
            if (ufValida.equalsIgnoreCase(uf)) {
                return true;
            }
        }

        return false;
    }
}
