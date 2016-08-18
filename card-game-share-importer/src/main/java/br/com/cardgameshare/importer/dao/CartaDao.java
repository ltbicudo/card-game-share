package br.com.cardgameshare.importer.dao;


import br.com.cardgameshare.importer.util.ResultSetUtil;
import br.com.cardgameshare.importer.util.SQLUtil;
import br.com.cardgameshare.util.DateUtil;
import com.mysql.jdbc.JDBC4ResultSet;

import java.sql.*;
import java.util.Date;

public class CartaDao {

    public static final String TABELA = "CARTA";
    public static final String COLUNA_ID = "ID";
    public static final String COLUNA_NOME = "NOME";
    public static final String COLUNA_CUSTO_MANA_CONVERTIDO = "CUSTO_MANA_CONVERTIDO";
    public static final String COLUNA_CUSTO_MANA = "CUSTO_MANA";
    public static final String COLUNA_NUMERO = "NUMERO";
    public static final String COLUNA_TEXTO = "TEXTO";
    public static final String COLUNA_TIPO = "TIPO";
    public static final String COLUNA_PODER = "PODER";
    public static final String COLUNA_RESISTENCIA = "RESISTENCIA";
    public static final String COLUNA_LEALDADE = "LEALDADE";
    public static final String COLUNA_MULTIVERSE_ID = "MULTIVERSE_ID";
    public static final String COLUNA_TEXTO_ORIGINAL = "TEXTO_ORIGINAL";
    public static final String COLUNA_CITACAO = "CITACAO";
    public static final String COLUNA_JSON_ID = "JSON_ID";
    public static final String COLUNA_RARIDADE = "ID_RARIDADE";
    public static final String COLUNA_LAYOUT = "ID_LAYOUT";
    public static final String COLUNA_ARTISTA = "ID_ARTISTA";
    public static final String COLUNA_COLECAO = "ID_COLECAO";

    private static final String SELECT_COMPLETO = SQLUtil.obterSelectCompletoTabela(CartaDao.class);

    private Connection conn;

    public CartaDao(Connection conn) {
        this.conn = conn;
    }

    public ResultSet buscarPorJsonId(String jsonId) {
        try {
            PreparedStatement sql =
                    conn.prepareStatement(SELECT_COMPLETO + " WHERE " + COLUNA_JSON_ID + " = ? ");
            sql.setString(1, jsonId);
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
