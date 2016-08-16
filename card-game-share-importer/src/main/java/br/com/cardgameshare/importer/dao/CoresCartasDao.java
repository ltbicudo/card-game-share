package br.com.cardgameshare.importer.dao;


import br.com.cardgameshare.importer.util.SQLUtil;

import java.sql.Connection;

public class CoresCartasDao {


    public static final String TABELA = "CORES_CARTAS";
    public static final String COLUNA_CARTA = "ID_CARTA";
    public static final String COLUNA_COR = "ID_COR";

    private static final String SELECT_COMPLETO = SQLUtil.obterSelectCompletoTabela(CoresCartasDao.class);

    private Connection conn;

    public CoresCartasDao(Connection conn) {
        this.conn = conn;
    }

}
