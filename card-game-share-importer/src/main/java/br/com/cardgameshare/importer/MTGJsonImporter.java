package br.com.cardgameshare.importer;

import br.com.cardgameshare.entity.Carta;
import br.com.cardgameshare.service.ImportacaoService;
import br.com.cardgameshare.service.UsuarioService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.InitialContext;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Iterator;

public class MTGJsonImporter {

    private static final String JSON_FOLDER_NAME = "2016-07";
    private static final String JSON_FILE_NAME = "AllSets-x.json";

    public static void main(String args[]) throws Exception {



        ImportacaoService service = (ImportacaoService) new InitialContext().lookup("java:comp/env/ImportacaoService");
//        ImportacaoService service = (ImportacaoService) EJBContainer.createEJBContainer().getContext().lookup("java:global/lookup-of-ejbs/ImportacaoService");
//        InitialContext ic = new InitialContext();
//        ImportacaoService service = (ImportacaoService) ic.lookup("br.com.cardgameshare.service.ImportacaoService");

        JSONParser jsonParser = new JSONParser();
        URL urlArquivo = MTGJsonImporter.class.getResource("json" + "/" + JSON_FOLDER_NAME + "/" + JSON_FILE_NAME);
        //File arquivo = new File(urlArquivo.toURI());
        File arquivo = new File("C:\\Flavia\\Particular\\projetos\\card-game-share\\card-game-share-importer\\src\\main\\resources\\json\\2016-07\\AllSets-x.json");

        Object obj = jsonParser.parse(new FileReader(arquivo));

        JSONObject jsonObject = (JSONObject) obj;

        JSONArray cartas = (JSONArray)((JSONObject) jsonObject.get("LEA")).get("cards");

        System.out.println("Cartas: "+cartas);

        Iterator<JSONObject> iterator = cartas.iterator();
        while (iterator.hasNext()){
            Carta carta = new Carta();
            carta.setCustoManaConvertido((Long) iterator.next().get("cmc"));
            service.cadastrar(carta);

        }

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
