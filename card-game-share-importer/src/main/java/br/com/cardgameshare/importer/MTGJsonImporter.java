package br.com.cardgameshare.importer;

import br.com.cardgameshare.importer.exception.ImporterException;
import br.com.cardgameshare.importer.properties.PropertiesKeyEnum;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

public class MTGJsonImporter {

    private static Properties importerProperties;

    static {

        try {
            InputStream in = MTGJsonImporter.class.getResourceAsStream("/importer.properties");
            importerProperties = new Properties();
            importerProperties.load(in);
            in.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Problemas na leitura do properties! Execucao cancelada");
        }
    }

    public static void main(String args[]) {

        try {

            // Leitura do arquivo de importacao
            // Para visualizar arquivo JSON online: http://www.jsoneditoronline.org/
            JSONObject jsonObject = lerArquivoImportacao();

            // Validação do tipo de arquivo importado
            String tipoArquivo = (String) importerProperties.get(PropertiesKeyEnum.JSON_FILE_TYPE.getValor());

            if ("SET".equals(tipoArquivo)) {
                realizarImportacaoArquivoSet(jsonObject);
            } else {
                throw new ImporterException("Tipo de arquivo não suportado!");
            }

        } catch (ImporterException e) {
            System.err.println("Execução MTGJsonImporter encontrou problemas e não finalizou!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

    }

    private static JSONObject lerArquivoImportacao() throws ImporterException {
        try {
            JSONParser jsonParser = new JSONParser();
            URL urlArquivo = MTGJsonImporter.class.getResource("/" + "json" + "/" + importerProperties.get(PropertiesKeyEnum.JSON_FOLDER_NAME.getValor()) + "/" + importerProperties.get(PropertiesKeyEnum.JSON_FILE_NAME.getValor()));
            File arquivo = new File(urlArquivo.toURI());

            Object obj = jsonParser.parse(new FileReader(arquivo));
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;
        } catch (FileNotFoundException fnfe) {
            throw new ImporterException("Arquivo properties não encontrado!", fnfe);
        } catch (URISyntaxException urise) {
            throw new ImporterException("URI do arquivo properties é inválida!", urise);
        } catch (IOException ioe) {
            throw new ImporterException("Problemas de IO!", ioe);
        } catch (org.json.simple.parser.ParseException pe) {
            throw new ImporterException("Problemas no parse do arquivo properties!", pe);
        }
    }

    private static void realizarImportacaoArquivoSet(JSONObject jsonObject) throws ImporterException {

        System.out.println(jsonObject.get("name"));
        System.out.println(jsonObject.get("code"));
        System.out.println(jsonObject.get("releaseDate"));
        System.out.println(jsonObject.get("border"));
        System.out.println(jsonObject.get("type"));
        System.out.println(jsonObject.get("block"));

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

    /**
     *
     *    {
     *    "age":100,
     *    "name":"mkyong.com",
     *    "messages":["msg 1","msg 2","msg 3"]
     *    }
     *
     *    JSONObject jsonObject = (JSONObject) obj;
     *
     *    String name = (String) jsonObject.get("name");
     *    System.out.println(name);
     *
     *    long age = (Long) jsonObject.get("age");
     *    System.out.println(age);
     *
     *    // loop array
     *    JSONArray msg = (JSONArray) jsonObject.get("messages");
     *    Iterator<String> iterator = msg.iterator();
     *    while (iterator.hasNext()) {
     *    System.out.println(iterator.next());
     }
     */

}
