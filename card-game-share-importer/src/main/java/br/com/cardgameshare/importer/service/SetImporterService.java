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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class SetImporterService {

    private Connection conn;
    private StringBuilder resultadoImportacao;

    private GenericDao genericDao;
    private ColecaoDao colecaoDao;
    private BordaDao bordaDao;
    private TipoColecaoDao tipoColecaoDao;
    private BlocoDao blocoDao;
    private CartaDao cartaDao;
    private RaridadeDao raridadeDao;
    private TipoCartaDao tipoCartaDao;
    private CorDao corDao;
    private LayoutDao layoutDao;
    private ArtistaDao artistaDao;

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

            // Exibição dos resultados
            System.out.print(this.resultadoImportacao.toString());

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

        this.resultadoImportacao.append("Iniciando importação da coleção ").append((String) jsonObject.get("name")).append(" (").append((String) jsonObject.get("code")).append(")\n");
        // Validação de pré-existência da coleção
        ResultSet resultadoConsulta = this.colecaoDao.buscarPorCodigo((String) jsonObject.get("code"));
        if (resultadoConsulta == null) {

            ResultSet borda = this.bordaDao.buscarPorCodigo((String) jsonObject.get("border"));
            if (borda == null) {
                throw new IllegalArgumentException("Borda não encontrada: " + (String) jsonObject.get("border"));
            }
            ResultSet tipo = this.tipoColecaoDao.buscarPorCodigo((String) jsonObject.get("type"));
            if (tipo == null) {
                throw new IllegalArgumentException("Tipo de coleção não encontrado: " + (String) jsonObject.get("type"));
            }
            boolean colecaoPossuiBloco = false;
            ResultSet bloco = null;
            if (jsonObject.get("block") != null) {
                colecaoPossuiBloco = true;
                bloco = this.blocoDao.buscarPorNome((String) jsonObject.get("block"));
                if (bloco == null) {
                    List<ParametroDTO> listaParametrosInsercaoBloco = new ArrayList<ParametroDTO>();
                    listaParametrosInsercaoBloco.add(new ParametroDTO(BlocoDao.COLUNA_NOME, (String) jsonObject.get("block"), Types.VARCHAR));
                    this.genericDao.inserir(BlocoDao.TABELA, listaParametrosInsercaoBloco);
                    bloco = this.blocoDao.buscarPorNome((String) jsonObject.get("block"));
                }
            }

            List<ParametroDTO> listaParametrosInsercaoColecao = new ArrayList<ParametroDTO>();
            listaParametrosInsercaoColecao.add(new ParametroDTO(ColecaoDao.COLUNA_NOME, (String) jsonObject.get("name"), Types.VARCHAR));
            listaParametrosInsercaoColecao.add(new ParametroDTO(ColecaoDao.COLUNA_CODIGO, (String) jsonObject.get("code"), Types.VARCHAR));
            listaParametrosInsercaoColecao.add(new ParametroDTO(ColecaoDao.COLUNA_DATA_LANCAMENTO, DateUtil.converterStringEmData((String) jsonObject.get("releaseDate"), "yyyy-MM-dd"), Types.DATE));
            listaParametrosInsercaoColecao.add(new ParametroDTO(ColecaoDao.COLUNA_BORDA, borda.getLong(BordaDao.COLUNA_ID), Types.NUMERIC));
            listaParametrosInsercaoColecao.add(new ParametroDTO(ColecaoDao.COLUNA_TIPO, tipo.getLong(TipoColecaoDao.COLUNA_ID), Types.NUMERIC));
            if (colecaoPossuiBloco) {
                listaParametrosInsercaoColecao.add(new ParametroDTO(ColecaoDao.COLUNA_BLOCO, bloco.getLong(TipoColecaoDao.COLUNA_ID), Types.NUMERIC));
            } else {
                listaParametrosInsercaoColecao.add(new ParametroDTO(ColecaoDao.COLUNA_BLOCO, null, Types.NUMERIC));
            }
            this.genericDao.inserir(ColecaoDao.TABELA, listaParametrosInsercaoColecao);

            resultadoConsulta = this.colecaoDao.buscarPorCodigo((String) jsonObject.get("code"));
        } else {
            this.resultadoImportacao.append("Coleção já existente no banco de dados e terá apenas as cartas importadas\n");
        }
        return resultadoConsulta.getLong(ColecaoDao.COLUNA_ID);
    }

    // TODO campos que ainda faltam importar
    /*
        OK
            nome String
            cmc Long
            custo mana String
            numero String
            raridade String
            citacao String
            jsonId String
            tipo String
            tipos [] String
            texto String
            texto original String
            cores [] String
            identificadores de cor [] String
            layout String
            artista String

        FAZER
            ataque - criatura
            defesa - criatura
            lealdade - planeswalker
            subtipos
            fazer uma varredura nos demais atributos criados pela Flavia

        NOK
            nomes estrangeiros [] Objeto
            nome da imagem String
            legalidades [] Objeto
            multiverse id Long
            tipo original String
            printings [] String
     */

    private void importarInformacoesCartas(JSONObject jsonObject, Long idColecao) throws Exception {

        this.resultadoImportacao.append("\nIniciando importação das cartas\n");
        JSONArray cartas = (JSONArray) jsonObject.get("cards");

        Iterator<JSONObject> iterator = cartas.iterator();
        List<ParametroDTO> listaParametrosInsercaoCarta;
        int contadorCartasImportadas = 0;
        while (iterator.hasNext()) {
            JSONObject cartaAtual = iterator.next();
            String identificacaoCartaLog = (String)cartaAtual.get("name") + " (" + (String) cartaAtual.get("id") + ")";

            ResultSet cartaConsultada = this.cartaDao.buscarPorJsonId((String) cartaAtual.get("id"));
            if (cartaConsultada != null) {
                this.resultadoImportacao.append(identificacaoCartaLog).append(" já existente no banco de dados e não será atualizada\n");
                continue;
            }

            listaParametrosInsercaoCarta = new ArrayList<ParametroDTO>();
            listaParametrosInsercaoCarta.add(new ParametroDTO(CartaDao.COLUNA_NOME, (String)cartaAtual.get("name"), Types.VARCHAR));
            listaParametrosInsercaoCarta.add(new ParametroDTO(CartaDao.COLUNA_CUSTO_MANA_CONVERTIDO, (Long)cartaAtual.get("cmc"), Types.NUMERIC));
            listaParametrosInsercaoCarta.add(new ParametroDTO(CartaDao.COLUNA_CUSTO_MANA, (String)cartaAtual.get("manaCost"), Types.VARCHAR));
            listaParametrosInsercaoCarta.add(new ParametroDTO(CartaDao.COLUNA_NUMERO, (String) cartaAtual.get("number"), Types.VARCHAR));
            listaParametrosInsercaoCarta.add(new ParametroDTO(CartaDao.COLUNA_TIPO, (String) cartaAtual.get("type"), Types.VARCHAR));
            listaParametrosInsercaoCarta.add(new ParametroDTO(CartaDao.COLUNA_TEXTO, (String) cartaAtual.get("text"), Types.VARCHAR));
            listaParametrosInsercaoCarta.add(new ParametroDTO(CartaDao.COLUNA_TEXTO_ORIGINAL, (String) cartaAtual.get("originalText"), Types.VARCHAR));
            ResultSet raridade = this.raridadeDao.buscarPorCodigo((String) cartaAtual.get("rarity")); // FIXME ajustar para não consultar a rairdade para cada carta
            if (raridade == null) {
                throw new IllegalArgumentException("Raridade não encontrada: " + (String) cartaAtual.get("rarity"));
            }
            ResultSet layout = this.layoutDao.buscarPorCodigo((String) cartaAtual.get("layout")); // FIXME ajustar para não consultar o layout para cada carta
            if (layout == null) {
                throw new IllegalArgumentException("Layout não encontrado: " + (String) cartaAtual.get("layout"));
            }
            ResultSet artista = this.artistaDao.buscarPorNome((String) cartaAtual.get("artist")); // FIXME ajustar para não consultar o artista para cada carta
            if (artista == null) {
                List<ParametroDTO> listaParametrosInsercaoArtista = new ArrayList<ParametroDTO>();
                listaParametrosInsercaoArtista.add(new ParametroDTO(ArtistaDao.COLUNA_NOME, (String) cartaAtual.get("artist"), Types.VARCHAR));
                this.genericDao.inserir(ArtistaDao.TABELA, listaParametrosInsercaoArtista);
                artista = this.artistaDao.buscarPorNome((String) cartaAtual.get("artist"));
            }
            listaParametrosInsercaoCarta.add(new ParametroDTO(CartaDao.COLUNA_CITACAO, (String) cartaAtual.get("flavor"), Types.VARCHAR));
            listaParametrosInsercaoCarta.add(new ParametroDTO(CartaDao.COLUNA_JSON_ID, (String) cartaAtual.get("id"), Types.VARCHAR));
            listaParametrosInsercaoCarta.add(new ParametroDTO(CartaDao.COLUNA_RARIDADE, raridade.getLong(RaridadeDao.COLUNA_ID), Types.NUMERIC));
            listaParametrosInsercaoCarta.add(new ParametroDTO(CartaDao.COLUNA_LAYOUT, layout.getLong(LayoutDao.COLUNA_ID), Types.NUMERIC));
            listaParametrosInsercaoCarta.add(new ParametroDTO(CartaDao.COLUNA_ARTISTA, artista.getLong(LayoutDao.COLUNA_ID), Types.NUMERIC));
            listaParametrosInsercaoCarta.add(new ParametroDTO(CartaDao.COLUNA_COLECAO, idColecao, Types.NUMERIC));

            this.genericDao.inserir(CartaDao.TABELA, listaParametrosInsercaoCarta);

            ResultSet carta = this.cartaDao.buscarPorJsonId((String) cartaAtual.get("id"));
            if (carta == null) {
                throw new IllegalArgumentException("Carta recém inserida não foi encontrada: " + identificacaoCartaLog);
            }

            JSONArray tipos = (JSONArray) cartaAtual.get("types");
            Iterator<String> iteratorTipos = tipos.iterator();
            while (iteratorTipos.hasNext()) {
                String tipoAtual = iteratorTipos.next();
                ResultSet tipoCarta = this.tipoCartaDao.buscarPorCodigo(tipoAtual); // FIXME ajustar para não consultar o tipo de carta para cada carta
                if (tipoCarta == null) {
                    throw new IllegalArgumentException("Tipo de Carta não encontrado: " + tipoAtual);
                }
                List<ParametroDTO> listaParametrosInsercaoTiposCartas = new ArrayList<ParametroDTO>();
                listaParametrosInsercaoTiposCartas.add(new ParametroDTO(TiposCartasDao.COLUNA_CARTA, carta.getLong(CartaDao.COLUNA_ID), Types.NUMERIC));
                listaParametrosInsercaoTiposCartas.add(new ParametroDTO(TiposCartasDao.COLUNA_TIPO_CARTA, tipoCarta.getLong(TipoCartaDao.COLUNA_ID), Types.NUMERIC));
                this.genericDao.inserir(TiposCartasDao.TABELA, listaParametrosInsercaoTiposCartas);
            }

            JSONArray cores = (JSONArray) cartaAtual.get("colors");
            if (cores != null) {
                Iterator<String> iteratorCores = cores.iterator();
                while (iteratorCores.hasNext()) {
                    String codigoCorAtual = iteratorCores.next();
                    ResultSet cor = this.corDao.buscarPorCodigo(codigoCorAtual); // FIXME ajustar para não consultar a cor para cada carta
                    if (cor == null) {
                        throw new IllegalArgumentException("Cor não encontrada: " + codigoCorAtual);
                    }

                    List<ParametroDTO> listaParametrosInsercaoCoresCartas = new ArrayList<ParametroDTO>();
                    listaParametrosInsercaoCoresCartas.add(new ParametroDTO(CoresCartasDao.COLUNA_CARTA, carta.getLong(CartaDao.COLUNA_ID), Types.NUMERIC));
                    listaParametrosInsercaoCoresCartas.add(new ParametroDTO(CoresCartasDao.COLUNA_COR, cor.getLong(CorDao.COLUNA_ID), Types.NUMERIC));
                    this.genericDao.inserir(CoresCartasDao.TABELA, listaParametrosInsercaoCoresCartas);
                }
            }

            JSONArray identificadoresCores = (JSONArray) cartaAtual.get("colorIdentity");
            if (cores != null) {
                Iterator<String> iteratorIdentificadoresCores = identificadoresCores.iterator();
                while (iteratorIdentificadoresCores.hasNext()) {
                    String identificadorCorAtual = iteratorIdentificadoresCores.next();
                    ResultSet cor = this.corDao.buscarPorSigla(identificadorCorAtual); // FIXME ajustar para não consultar a cor para cada carta
                    if (cor == null) {
                        throw new IllegalArgumentException("Identificador da cor não encontrada: " + identificadorCorAtual);
                    }

                    List<ParametroDTO> listaParametrosInsercaoIdentificadoresCoresCartas = new ArrayList<ParametroDTO>();
                    listaParametrosInsercaoIdentificadoresCoresCartas.add(new ParametroDTO(IdentificadoresCoresCartasDao.COLUNA_CARTA, carta.getLong(CartaDao.COLUNA_ID), Types.NUMERIC));
                    listaParametrosInsercaoIdentificadoresCoresCartas.add(new ParametroDTO(IdentificadoresCoresCartasDao.COLUNA_COR, cor.getLong(CorDao.COLUNA_ID), Types.NUMERIC));
                    this.genericDao.inserir(IdentificadoresCoresCartasDao.TABELA, listaParametrosInsercaoIdentificadoresCoresCartas);
                }
            }
            contadorCartasImportadas++;
            this.resultadoImportacao.append(identificacaoCartaLog).append(" importada para o banco de dados\n");
        }

        this.resultadoImportacao.append("\nFinalização da importação das cartas\n");
        this.resultadoImportacao.append("Total cartas na coleção: ").append(cartas.size()).append("\n");
        this.resultadoImportacao.append("Total cartas importadas: ").append(contadorCartasImportadas);
    }

    private void prepararService() {

        try {

            // Abertura conexão
            this.conn = DriverManager.getConnection(
                    (String) this.importerProperties.get(PropertiesKeyEnum.DATABASE_URL.getValor()),
                    (String) this.importerProperties.get(PropertiesKeyEnum.DATABASE_USER.getValor()),
                    (String) this.importerProperties.get(PropertiesKeyEnum.DATABASE_PASSWORD.getValor()));
            this.conn.setAutoCommit(false);

            // Resultado importação
            this.resultadoImportacao = new StringBuilder();

            // Inicialização dos DAOs
            this.genericDao = new GenericDao(this.conn);
            this.colecaoDao = new ColecaoDao(this.conn);
            this.bordaDao = new BordaDao(this.conn);
            this.tipoColecaoDao = new TipoColecaoDao(this.conn);
            this.blocoDao = new BlocoDao(this.conn);
            this.cartaDao = new CartaDao(this.conn);
            this.raridadeDao = new RaridadeDao(this.conn);
            this.tipoCartaDao = new TipoCartaDao(this.conn);
            this.corDao = new CorDao(this.conn);
            this.layoutDao = new LayoutDao(this.conn);
            this.artistaDao = new ArtistaDao(this.conn);

        } catch (SQLException e) {
            SQLUtil.tratarSQLException(e);
        }

    }


}
