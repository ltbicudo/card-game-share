package br.com.cardgameshare.importer.dao;


import br.com.cardgameshare.importer.util.ResultSetUtil;
import br.com.cardgameshare.importer.util.SQLUtil;
import br.com.cardgameshare.util.DateUtil;
import com.mysql.jdbc.JDBC4ResultSet;

import java.sql.*;
import java.util.Date;

public class ColecaoDao {

    public static final String TABELA = "COLECAO";
    public static final String COLUNA_ID = "ID";
    public static final String COLUNA_NOME = "NOME";
    public static final String COLUNA_CODIGO = "CODIGO";
    public static final String COLUNA_DATA_LANCAMENTO = "DATA_LANCAMENTO";
    public static final String COLUNA_BORDA = "ID_BORDA";
    public static final String COLUNA_TIPO = "ID_TIPO_COLECAO";
    public static final String COLUNA_BLOCO = "ID_BLOCO";

    private static final String SELECT_COMPLETO = SQLUtil.obterSelectCompletoTabela(ColecaoDao.class);
    private static final String INSERT_COMPLETO = SQLUtil.obterInsertCompletoTabela(ColecaoDao.class);

    private Connection conn;

    public ColecaoDao(Connection conn) {
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

    public void inserir(String nome, String codigo, Date dataLancamento, Long borda, Long tipo, Long bloco) {

        try {
            PreparedStatement sql = conn.prepareStatement(INSERT_COMPLETO);
            sql.setString(1, nome);
            sql.setString(2, codigo);
            sql.setDate(3, DateUtil.converterDataUtilEmDataSQL(dataLancamento));
            sql.setLong(4, borda);
            sql.setLong(5, tipo);
            sql.setLong(6, bloco);
            sql.executeUpdate();
        } catch (SQLException e) {
            SQLUtil.tratarSQLException(e);
        }

    }

}
