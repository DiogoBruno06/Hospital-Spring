package com.cadastro.hospital.util;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatarData {
    @SneakyThrows
    public static String formatarData(Date data) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(data);
    }

}
