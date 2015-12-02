/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geocoso3;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author alessandro
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private SplitPane splittedPane;
    
    
    
    @FXML
    private Button search;
    
    @FXML
    private ImageView weatherImage, icon;
    
    @FXML
    private Label lblName, lblWeather, lblTemperature, lblHumidity, lblPressure, lblWind;
    @FXML
    private TextField testField;
    @FXML
    private Label wait;
    
    
  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblName.setText(null);
        lblWeather.setText(null);
        lblTemperature.setText(null);
        lblHumidity.setText(null);
        lblPressure.setText(null);
        lblWind.setText(null);
        weatherImage.setVisible(false);
        splittedPane.setStyle("-fx-background-image: url('/immagini/potatoman.jpg');");
        wait.setText(null);
    }    

    @FXML
    private void clicked(MouseEvent event) throws UnsupportedEncodingException, IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        System.out.println("sono qua");
        wait.setText("Please wait...");
        XMLLocation xl = new XMLLocation(testField.getText());
        System.out.println(testField.getText()+"");
        xl.locationXML();
        System.out.println("sei arrivato qui");
        XMLWeather xmlWeather = new XMLWeather(xl.getLocation());
        System.out.println("ora inserisci i dati");
        Weather weather = xmlWeather.getWeather();
        Image image = new Image(weather.getIcon());
        wait.setText(null);
        lblName.setText(xl.getLocation().getName());
        lblWeather.setText(weather.getDescription());
        lblTemperature.setText(weather.getTemperature());
        lblHumidity.setText(weather.getHumidity());
        lblPressure.setText(weather.getPressure());
        lblWind.setText(weather.getWind()+"m/s");
        weatherImage.setVisible(true);
        weatherImage.setImage(image);
        System.out.println("Fatto");
    }
    
}
