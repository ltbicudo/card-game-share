package br.com.cardgameshare.importer.util;

import java.lang.reflect.Field;
import java.sql.SQLException;

public class SQLUtil {

    private SQLUtil() {

    }

    public static String obterSelectCompletoTabela(Class clazz) {

        try {

            StringBuilder sql = new StringBuilder();

            Field campoTabela = clazz.getDeclaredField("TABELA");
            String nomeTabela = (String) campoTabela.get(clazz);

            sql.append("SELECT ");

            Field[] campos = clazz.getDeclaredFields();
            String token = "";
            for (Field campoAtual : campos) {
                if (campoAtual.getName().startsWith("COLUNA_")) {
                    sql.append(token);
                    sql.append(campoAtual.get(clazz));
                    token = ", ";
                }
            }

            sql.append(" FROM ");
            sql.append(nomeTabela);

            return sql.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public static String obterInsertCompletoTabela(Class clazz) {

        try {

            StringBuilder sql = new StringBuilder();

            Field campoTabela = clazz.getDeclaredField("TABELA");
            String nomeTabela = (String) campoTabela.get(clazz);

            sql.append("INSERT INTO ");
            sql.append(nomeTabela);

            Field[] campos = clazz.getDeclaredFields();
            String token = "";
            int contadorCampos = 0;

            sql.append(" (");
            for (Field campoAtual : campos) {
                if (campoAtual.getName().startsWith("COLUNA_")
                        && !campoAtual.getName().equals("COLUNA_ID")) {
                    sql.append(token);
                    sql.append(campoAtual.get(clazz));
                    token = ", ";
                    contadorCampos++;
                }
            }
            sql.append(") ");

            sql.append(" VALUES ");
            token = "(";
            for (int i = 0 ; i < contadorCampos ; i++) {
                sql.append(token);
                sql.append("?");
                token = ", ";
            }
            sql.append(")");

            return sql.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public static void tratarSQLException(SQLException e) {
        System.out.println("SQLException: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
        e.printStackTrace();
    }

}
