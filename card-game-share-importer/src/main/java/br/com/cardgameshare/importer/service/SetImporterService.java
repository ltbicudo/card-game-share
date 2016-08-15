package br.com.cardgameshare.importer.service;

import br.com.cardgameshare.importer.dao.BordaDao;
import br.com.cardgameshare.importer.dao.ColecaoDao;
import br.com.cardgameshare.importer.exception.ImporterException;
import br.com.cardgameshare.importer.properties.PropertiesKeyEnum;
import br.com.cardgameshare.util.DateUtil;
import org.json.simple.JSONObject;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class SetImporterService {

    private Connection conn;
    private ColecaoDao colecaoDao;
    private BordaDao bordaDao;
    private Properties importerProperties;

    public SetImporterService(Properties importerProperties) {
        this.importerProperties = importerProperties;
    }

    public void importar(JSONObject jsonObject) throws ImporterException {

        try {

            // Abrir conexão e criar DAOs
            this.prepararService();

            // Coleção
            importarInformacoesColecao(jsonObject);

            // Fechando a conexão
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

    private void importarInformacoesColecao(JSONObject jsonObject) throws Exception {

        // Validação de pré-existência da coleção
        ResultSet resultadoConsulta = this.colecaoDao.buscarPorCodigo((String) jsonObject.get("code"));
        if (resultadoConsulta == null) {

            ResultSet borda = this.bordaDao.buscarPorCodigo((String) jsonObject.get("border"));

            this.colecaoDao.inserir(
                    (String) jsonObject.get("name"),
                    (String) jsonObject.get("code"),
                    DateUtil.converterStringEmData((String) jsonObject.get("releaseDate"), "yyyy-MM-dd"),
                    borda.getLong(BordaDao.COLUNA_ID));
            resultadoConsulta = this.colecaoDao.buscarPorCodigo((String) jsonObject.get("code"));
        }

//            JSONArray cartas = (JSONArray)((JSONObject) jsonObject.get("LEA")).get("cards");
//
//            System.out.println("Cartas: "+cartas);
//
//            Iterator<JSONObject> iterator = cartas.iterator();
//            while (iterator.hasNext()){
//                //Carta carta = new Carta();
//                //carta.setCustoManaConvertido((Long) iterator.next().get("cmc"));
//                //service.cadastrar(carta);
//
//            }

    }

    private void prepararService() {

        try {

            // Abertura conexão
            this.conn = DriverManager.getConnection(
                    (String) this.importerProperties.get(PropertiesKeyEnum.DATABASE_URL.getValor()),
                    (String) this.importerProperties.get(PropertiesKeyEnum.DATABASE_USER.getValor()),
                    (String) this.importerProperties.get(PropertiesKeyEnum.DATABASE_PASSWORD.getValor()));

            // Inicialização dos DAOs
            this.colecaoDao = new ColecaoDao(this.conn);
            this.bordaDao = new BordaDao(this.conn);

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }


}
