package br.com.cardgameshare.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private DateUtil() {

    }

    public static Date converterStringEmData(String valor, String formato) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            return sdf.parse(valor);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static java.sql.Date converterDataUtilEmDataSQL(Date valor) {
        if (valor == null) {
            return null;
        }
        return new java.sql.Date(valor.getTime());
    }

}
