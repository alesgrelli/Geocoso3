/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geocoso3;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author alessandro
 */
public class Geocoso3 extends Application {
   
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParserConfigurationException, XPathExpressionException, SAXException {
        launch(args);
        /*
        System.out.println("creo connessione");
        XMLLocation xl = new XMLLocation("Brescia");
        xl.locationXML();
        System.out.println("Ho trovato i dati della località");
        XMLWeather xmlWeather = new XMLWeather(xl.getLocation());
        System.out.println("programma terminato");
        */
    }
    
}
