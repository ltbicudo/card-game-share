package br.com.cardgameshare.importer.dao;

import br.com.cardgameshare.importer.dto.ParametroDTO;
import br.com.cardgameshare.importer.util.SQLUtil;
import br.com.cardgameshare.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

public class GenericDao {

    private Connection conn;

    public GenericDao(Connection conn) {
        this.conn = conn;
    }

    public void inserir(String tabela, List<ParametroDTO> parametros) {

        try {

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ");
            sql.append(tabela);
            sql.append(" (");

            String token = "";
            for (ParametroDTO parametroAtual : parametros) {
                sql.append(token);
                sql.append(parametroAtual.getColuna());
                token = ", ";
            }
            sql.append(")");
            sql.append(" VALUES ");
            sql.append(" ( ");

            token = "";
            for (ParametroDTO parametroAtual : parametros) {
                sql.append(token);
                sql.append("?");
                token = ", ";
            }
            sql.append(")");

            PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());

            int contadorParametros = 1;
            for (ParametroDTO parametroAtual : parametros) {
                if (Types.VARCHAR == parametroAtual.getTipoColuna()) {
                    if (parametroAtual.getValor() == null) {
                        preparedStatement.setNull(contadorParametros, Types.VARCHAR);
                    } else {
                        preparedStatement.setString(contadorParametros, (String) parametroAtual.getValor());
                    }
                }
                if (Types.NUMERIC == parametroAtual.getTipoColuna()) {
                    if (parametroAtual.getValor() == null) {
                        preparedStatement.setNull(contadorParametros, Types.NUMERIC);
                    } else {
                        preparedStatement.setLong(contadorParametros, (Long) parametroAtual.getValor());
                    }
                }
                if (Types.DATE == parametroAtual.getTipoColuna()) {
                    if (parametroAtual.getValor() == null) {
                        preparedStatement.setNull(contadorParametros, Types.DATE);
                    } else {
                        preparedStatement.setDate(contadorParametros, DateUtil.converterDataUtilEmDataSQL((Date) parametroAtual.getValor()));
                    }
                }
                if (Types.TIMESTAMP == parametroAtual.getTipoColuna()) {
                    if (parametroAtual.getValor() == null) {
                        preparedStatement.setNull(contadorParametros, Types.TIMESTAMP);
                    } else {
                        preparedStatement.setTimestamp(contadorParametros, DateUtil.converterDataUtilEmTimestampSQL((Date) parametroAtual.getValor()));
                    }
                }
                contadorParametros++;
            }

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            SQLUtil.tratarSQLException(e);
        }

    }

}
