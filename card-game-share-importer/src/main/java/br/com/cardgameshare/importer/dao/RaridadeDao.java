package br.com.cardgameshare.importer.dao;


import br.com.cardgameshare.importer.util.ResultSetUtil;
import br.com.cardgameshare.importer.util.SQLUtil;
import com.mysql.jdbc.JDBC4ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RaridadeDao {


    public static final String TABELA = "RARIDADE";
    public static final String COLUNA_ID = "ID";
    public static final String COLUNA_DESCRICAO = "DESCRICAO";
    public static final String COLUNA_CODIGO = "CODIGO";

    private static final String SELECT_COMPLETO = SQLUtil.obterSelectCompletoTabela(RaridadeDao.class);

    private Connection conn;

    public RaridadeDao(Connection conn) {
        this.conn = conn;
    }

    public ResultSet buscarPorCodigo(String codigo) {
        try {
            PreparedStatement sql =
                    conn.prepareStatement(SELECT_COMPLETO + " WHERE " + COLUNA_CODIGO + " = ? ");
            sql.setString(1, codigo);
            JDBC4ResultSet resultadoConsulta = (JDBC4ResultSet) sql.executeQuery();

            if (!ResultSetUtil.isEmpty(resultadoConsulta)) {
                resultadoConsulta.next();
                return resultadoConsulta;
            }

        } catch (SQLException e) {
            SQLUtil.tratarSQLException(e);
        }
        return null;
    }

}
