package br.com.cardgameshare.importer.dao;


import br.com.cardgameshare.importer.util.ResultSetUtil;
import br.com.cardgameshare.importer.util.SQLUtil;
import com.mysql.jdbc.JDBC4ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistaDao {


    public static final String TABELA = "ARTISTA";
    public static final String COLUNA_ID = "ID";
    public static final String COLUNA_NOME = "NOME";

    private static final String SELECT_COMPLETO = SQLUtil.obterSelectCompletoTabela(ArtistaDao.class);

    private Connection conn;

    public ArtistaDao(Connection conn) {
        this.conn = conn;
    }

    public ResultSet buscarPorNome(String nome) {
        try {
            PreparedStatement sql =
                    conn.prepareStatement(SELECT_COMPLETO + " WHERE " + COLUNA_NOME + " = ? ");
            sql.setString(1, nome);
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
