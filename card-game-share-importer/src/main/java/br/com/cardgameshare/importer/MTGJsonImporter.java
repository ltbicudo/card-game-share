package br.com.cardgameshare.importer;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.net.URL;

public class MTGJsonImporter {

    private static final String JSON_FOLDER_NAME = "2016-07";
    private static final String JSON_FILE_NAME = "AllSets-x.json";

    public static void main(String args[]) throws Exception {

        JSONParser jsonParser = new JSONParser();
        URL urlArquivo = MTGJsonImporter.class.getResource(
                File.separator + "json" + File.separator + JSON_FOLDER_NAME + File.separator + JSON_FILE_NAME);
        File arquivo = new File(urlArquivo.toURI());

        Object obj = jsonParser.parse(new FileReader(arquivo));

        JSONObject jsonObject = (JSONObject) obj;

        System.out.println(((JSONObject) jsonObject.get("LEA")).get("cards"));

        // Verificar exemplos de leitura de tipos diferentes de objetos abaixo

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
