package br.com.cardgameshare.importer.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class ColecaoDao {

    public void inserir(String nome, String codigo, Date dataLancamento) {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/card_game_share?UseUnicode=true&amp;characterEncoding=utf-8", "root", "root");

            PreparedStatement sql = conn.prepareStatement("INSERT INTO COLECAO (NOME, CODIGO, DATA_LANCAMENTO) VALUES (?, ?, ?)");
            sql.setString(1, nome);
            sql.setString(2, codigo);
            sql.setDate(3, new java.sql.Date(dataLancamento.getTime()));
            sql.executeUpdate();

            conn.close();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }

}
