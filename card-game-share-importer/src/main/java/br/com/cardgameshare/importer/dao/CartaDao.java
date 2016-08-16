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
    public static final String COLUNA_NUMERO = "NUMERO";
    public static final String COLUNA_RARIDADE = "ID_RARIDADE";
    public static final String COLUNA_COLECAO = "ID_COLECAO";

    private static final String SELECT_COMPLETO = SQLUtil.obterSelectCompletoTabela(CartaDao.class);

    private Connection conn;

    public CartaDao(Connection conn) {
        this.conn = conn;
    }

}
