package br.com.cardgameshare.importer.service;

import br.com.cardgameshare.importer.dao.*;
import br.com.cardgameshare.importer.dto.ParametroDTO;
import br.com.cardgameshare.importer.exception.ImporterException;
import br.com.cardgameshare.importer.properties.PropertiesKeyEnum;
import br.com.cardgameshare.importer.util.SQLUtil;
import br.com.cardgameshare.util.DateUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class SetImporterService {

    private Connection conn;

    private GenericDao genericDao;
    private ColecaoDao colecaoDao;
    private BordaDao bordaDao;
    private TipoColecaoDao tipoColecaoDao;
    private BlocoDao blocoDao;
    private CartaDao cartaDao;
    private RaridadeDao raridadeDao;

    private Properties importerProperties;

    public SetImporterService(Properties importerProperties) {
        this.importerProperties = importerProperties;
    }

    public void importar(JSONObject jsonObject) throws ImporterException {

        try {

            // Abrir conexão e criar DAOs
            this.prepararService();

            // Coleção
            Long idColecao = importarInformacoesColecao(jsonObject);

            // Cartas
            importarInformacoesCartas(jsonObject, idColecao);

            // Fechando a conexão
            this.conn.commit();
            this.conn.close();

        } catch (Exception e) {
            // Fechando a conexão
            try {
                this.conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new ImporterException("Problemas ao importar arquivo SET", e);
        }
    }

    private Long importarInformacoesColecao(JSONObject jsonObject) throws Exception {

        // Validação de pré-existência da coleção
        ResultSet resultadoConsulta = this.colecaoDao.buscarPorCodigo((String) jsonObject.get("code"));
        if (resultadoConsulta == null) {

            ResultSet borda = this.bordaDao.buscarPorCodigo((String) jsonObject.get("border"));
            ResultSet tipo = this.tipoColecaoDao.buscarPorCodigo((String) jsonObject.get("type"));
            ResultSet bloco = this.blocoDao.buscarPorNome((String) jsonObject.get("block"));

            if (bloco == null) {
                List<ParametroDTO> listaParametrosInsercaoBloco = new ArrayList<ParametroDTO>();
                listaParametrosInsercaoBloco.add(new ParametroDTO(BlocoDao.COLUNA_NOME, (String) jsonObject.get("block"), Types.VARCHAR));
                this.genericDao.inserir(BlocoDao.TABELA, listaParametrosInsercaoBloco);
                bloco = this.blocoDao.buscarPorNome((String) jsonObject.get("block"));
            }

            List<ParametroDTO> listaParametrosInsercaoColecao = new ArrayList<ParametroDTO>();
            listaParametrosInsercaoColecao.add(new ParametroDTO(ColecaoDao.COLUNA_NOME, (String) jsonObject.get("name"), Types.VARCHAR));
            listaParametrosInsercaoColecao.add(new ParametroDTO(ColecaoDao.COLUNA_CODIGO, (String) jsonObject.get("code"), Types.VARCHAR));
            listaParametrosInsercaoColecao.add(new ParametroDTO(ColecaoDao.COLUNA_DATA_LANCAMENTO, DateUtil.converterStringEmData((String) jsonObject.get("releaseDate"), "yyyy-MM-dd"), Types.DATE));
            listaParametrosInsercaoColecao.add(new ParametroDTO(ColecaoDao.COLUNA_BORDA, borda.getLong(BordaDao.COLUNA_ID), Types.NUMERIC));
            listaParametrosInsercaoColecao.add(new ParametroDTO(ColecaoDao.COLUNA_TIPO, tipo.getLong(TipoColecaoDao.COLUNA_ID), Types.NUMERIC));
            listaParametrosInsercaoColecao.add(new ParametroDTO(ColecaoDao.COLUNA_BLOCO, bloco.getLong(TipoColecaoDao.COLUNA_ID), Types.NUMERIC));
            this.genericDao.inserir(ColecaoDao.TABELA, listaParametrosInsercaoColecao);

            resultadoConsulta = this.colecaoDao.buscarPorCodigo((String) jsonObject.get("code"));
        }
        return resultadoConsulta.getLong(ColecaoDao.COLUNA_ID);
    }

    private void importarInformacoesCartas(JSONObject jsonObject, Long idColecao) throws Exception {

        JSONArray cartas = (JSONArray) jsonObject.get("cards");

        Iterator<JSONObject> iterator = cartas.iterator();
        List<ParametroDTO> listaParametrosInsercaoCarta;
        while (iterator.hasNext()) {
            JSONObject cartaAtual = iterator.next();
            listaParametrosInsercaoCarta = new ArrayList<ParametroDTO>();
            listaParametrosInsercaoCarta.add(new ParametroDTO(CartaDao.COLUNA_NOME, (String)cartaAtual.get("name"), Types.VARCHAR));
            listaParametrosInsercaoCarta.add(new ParametroDTO(CartaDao.COLUNA_CUSTO_MANA_CONVERTIDO, (Long)cartaAtual.get("cmc"), Types.NUMERIC));
            listaParametrosInsercaoCarta.add(new ParametroDTO(CartaDao.COLUNA_NUMERO, (String) cartaAtual.get("number"), Types.VARCHAR));
            ResultSet raridade = this.raridadeDao.buscarPorCodigo((String) cartaAtual.get("rarity"));
            listaParametrosInsercaoCarta.add(new ParametroDTO(CartaDao.COLUNA_RARIDADE, raridade.getLong(RaridadeDao.COLUNA_ID), Types.NUMERIC));
            listaParametrosInsercaoCarta.add(new ParametroDTO(CartaDao.COLUNA_COLECAO, idColecao, Types.NUMERIC));

            this.genericDao.inserir(CartaDao.TABELA, listaParametrosInsercaoCarta);
        }
    }

    private void prepararService() {

        try {

            // Abertura conexão
            this.conn = DriverManager.getConnection(
                    (String) this.importerProperties.get(PropertiesKeyEnum.DATABASE_URL.getValor()),
                    (String) this.importerProperties.get(PropertiesKeyEnum.DATABASE_USER.getValor()),
                    (String) this.importerProperties.get(PropertiesKeyEnum.DATABASE_PASSWORD.getValor()));
            this.conn.setAutoCommit(false);

            // Inicialização dos DAOs
            this.genericDao = new GenericDao(this.conn);
            this.colecaoDao = new ColecaoDao(this.conn);
            this.bordaDao = new BordaDao(this.conn);
            this.tipoColecaoDao = new TipoColecaoDao(this.conn);
            this.blocoDao = new BlocoDao(this.conn);
            this.cartaDao = new CartaDao(this.conn);
            this.raridadeDao = new RaridadeDao(this.conn);

        } catch (SQLException e) {
            SQLUtil.tratarSQLException(e);
        }

    }


}
