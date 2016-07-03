package br.com.cardgameshare.importer;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class GathererImporter {

    public static void main(String args[]) throws Exception {

        URL gathererURL = new URL("http://gatherer.wizards.com/Pages/Search/Default.aspx?set=[%22Shadows%20over%20Innistrad%22]");
        URLConnection gathererURLConnection = gathererURL.openConnection();

        InputStream inputStream = gathererURLConnection.getInputStream();
        Scanner scanner = new Scanner(inputStream);

        while(scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }

        inputStream.close();

    }

}
