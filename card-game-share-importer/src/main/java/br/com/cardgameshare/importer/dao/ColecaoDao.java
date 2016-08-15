package br.com.cardgameshare.importer.dao;


import br.com.cardgameshare.importer.util.ResultSetUtil;
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

    private static final String SELECT_COMPLETO = "SELECT " + COLUNA_ID + ", " + COLUNA_NOME + ", " + COLUNA_CODIGO + ", " + COLUNA_DATA_LANCAMENTO + " FROM " + TABELA;
    private static final String INSERT_COMPLETO = "INSERT INTO " + TABELA + "(" + COLUNA_NOME + ", " + COLUNA_CODIGO + ", " + COLUNA_DATA_LANCAMENTO + ") VALUES (?, ?, ?)";

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

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    public void inserir(String nome, String codigo, Date dataLancamento) {

        try {
            PreparedStatement sql = conn.prepareStatement(INSERT_COMPLETO);
            sql.setString(1, nome);
            sql.setString(2, codigo);
            sql.setDate(3, DateUtil.converterDataUtilEmDataSQL(dataLancamento));
            sql.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }

}
