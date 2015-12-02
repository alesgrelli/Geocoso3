/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geocoso3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author alessandro
 */
public class XMLWeather {
    /**
     * Here I define all the XPathExpressions that I'm gonna need
     * in order to retrieve the informations about the weather.
     */
    private static String URL_WEATHER = "http://api.wunderground.com/api/2fe535a12fd3638f/forecast/conditions/q/";
    private static String PRESSURE = "/response/current_observation/pressure_in";
    private static String WIND = "/response/current_observation/wind_mph";
    private static String WEATHER = "/response/current_observation/weather";
    private static String TEMPERATURE = "/response/current_observation/temp_f";
    private static String HUMIDITY = "/response/current_observation/relative_humidity";
    private static String ICON = "/response/current_observation/icon_url";
    //private static String APY_KEY = "&appid=52ff3e9c7555f7d6633f3ca1e90b0de0";
    
    /**
     *
     */
    public URL URLWeather;

    /**
     *
     */
    public CreateConnection connection;

    /**
     *
     */
    public Location location;

    /**
     *
     */
    public Weather weather;
    
    /**
     *
     * @param location
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws XPathExpressionException
     */
    public XMLWeather(Location location) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException
    {
        this.location=location;
        connection = new CreateConnection(URLGetter());
        whatWeather();
        
    }
    
    
    /**
     * In order to use the OPENWEATHER API, I need a key, so I had to sign up to the webSite http://home.openweathermap.org
     * and get one.
     * @return 
     */
    public String URLGetter() throws UnsupportedEncodingException, MalformedURLException
    {
        StringBuilder builder = new StringBuilder(URL_WEATHER);
       
        builder.append(location.getLatitude());
        builder.append("," + location.getLongitude());
        builder.append(URLEncoder.encode(".xml"));
        String s = builder.toString();
        return s;
    }
    
    /**
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws XPathExpressionException
     */
    public void whatWeather() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException
    {
        System.out.println("connessione per dati meteo...");
        System.out.println(URLGetter());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(connection.getConnection());
            System.out.println("sei connesso a meteo");
            
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            
            /**
             * After creating the connection I use the XPathExpression I created previously.
             */
            System.out.println("Inizio la ricerca dei dati meteo...");
            XPathExpression pressure = xpath.compile(PRESSURE);
            XPathExpression wind = xpath.compile(WIND);
            XPathExpression weatherStatus = xpath.compile(WEATHER);
            XPathExpression temperature = xpath.compile(TEMPERATURE);
            //XPathExpression minTemperature = xpath.compile(MINTEMPERATURE);
            XPathExpression icon = xpath.compile(ICON);
            XPathExpression humidity = xpath.compile(HUMIDITY);
            
            /**
             * I put all the information I got into an instance of the weather class.
             * First I have to convert them into a String type.
             */
            System.out.println("Rcerca completta");
            weather = new Weather((String) temperature.evaluate(doc, XPathConstants.STRING), (String) humidity.evaluate(doc, XPathConstants.STRING),
                    (String) pressure.evaluate(doc, XPathConstants.STRING), (String) wind.evaluate(doc, XPathConstants.STRING),(String) weatherStatus.evaluate(doc, XPathConstants.STRING), (String) icon.evaluate(doc, XPathConstants.STRING));
            
            System.out.println(weather.toString());
    }
    
    public Weather getWeather()
    {
        return weather;
    }
    
    
}
