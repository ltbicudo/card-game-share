package br.com.cardgameshare.importer.dao;


import br.com.cardgameshare.importer.util.SQLUtil;

import java.sql.Connection;

public class TiposCartasDao {


    public static final String TABELA = "TIPOS_CARTAS";
    public static final String COLUNA_CARTA = "ID_CARTA";
    public static final String COLUNA_TIPO_CARTA = "ID_TIPO_CARTA";

    private static final String SELECT_COMPLETO = SQLUtil.obterSelectCompletoTabela(TiposCartasDao.class);

    private Connection conn;

    public TiposCartasDao(Connection conn) {
        this.conn = conn;
    }

}
