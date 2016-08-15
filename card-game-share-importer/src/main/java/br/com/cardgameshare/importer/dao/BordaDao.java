package br.com.cardgameshare.importer.dao;


import br.com.cardgameshare.importer.util.ResultSetUtil;
import br.com.cardgameshare.util.DateUtil;
import com.mysql.jdbc.JDBC4ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class BordaDao {


    public static final String TABELA = "BORDA";
    public static final String COLUNA_ID = "ID";
    public static final String COLUNA_DESCRICAO = "DESCRICAO";
    public static final String COLUNA_CODIGO = "CODIGO";

    private static final String SELECT_COMPLETO = "SELECT " + COLUNA_ID + ", " + COLUNA_DESCRICAO + ", " + COLUNA_CODIGO + " FROM " + TABELA;
    private static final String INSERT_COMPLETO = "INSERT INTO " + TABELA + "(" + COLUNA_DESCRICAO + ", " + COLUNA_CODIGO + ") VALUES (?, ?)";

    private Connection conn;

    public BordaDao(Connection conn) {
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
