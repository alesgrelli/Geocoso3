/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geocoso3;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import static java.lang.System.exit;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
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
public class XMLLocation {
    private Location location;
    private static  String URL_LOCATION = "http://maps.googleapis.com/maps/api/geocode/xml?address=";
    
    private static final String STATUS = "/GeocodeResponse/status/text()";
    private static final String STATUS_FAILURE = "NO_RESULT";
    private static final String searchedLat = "/GeocodeResponse/result/geometry/location/lat/text()";
    private static final String searchedLng = "/GeocodeResponse/result/geometry/location/lng/text()";
     private static final String address = "/GeocodeResponse/result/formatted_address/text()";
    
    private static URL URLLocation;
    
    public CreateConnection connection;
    
    /**
     *
     * @param name
     */
    public XMLLocation(String name) throws UnsupportedEncodingException, MalformedURLException
    {
            location = new Location(name);
            connection=new CreateConnection(generateURL());
    }
    
    public String generateURL() throws UnsupportedEncodingException, MalformedURLException
    {
        StringBuilder URLBuilder = new StringBuilder(URL_LOCATION);
        URLBuilder.append(URLEncoder.encode(location.getName(), "UTF-8"));
        String s = URLBuilder.toString();
        return s;
    }
    
    //Now I'm connected and I can search the XML for the location
    
    /**
     *
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws XPathExpressionException
     * @throws SAXException
     */
        
    
    public void locationXML() throws IOException, ParserConfigurationException, XPathExpressionException, SAXException
    {
        
        /**
        * creating a document and trying to connect to parse all the information I need
        */
        System.out.println("Sei arrivato qui");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(connection.getConnection());
            System.out.println("sei connesso a location");

            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();

            /**
             * Checking if found results
             */
            System.out.println("inizia Xpath");
            XPathExpression statusExpression = xpath.compile(STATUS);
            String status = (String) statusExpression.evaluate(doc, XPathConstants.STRING);
            if (status.equals(STATUS_FAILURE)) {
                System.out.println("Not Found");
            }

            /**
             * Getting latitude and longitude
             */
            XPathExpression latitude = xpath.compile(searchedLat);
            XPathExpression longitude = xpath.compile(searchedLng);
            XPathExpression addressName = xpath.compile(address);
            
            
            /**
             * In order to set the attributes in my Location instance
             * I need to cast latitude, longitude and addressName as String types.
             */
            System.out.println("Ricerca...");
            String latitudeString = (String) latitude.evaluate(doc, XPathConstants.STRING);
            String longitudeString = (String) longitude.evaluate(doc, XPathConstants.STRING);
            String addressNameString = (String) addressName.evaluate(doc, XPathConstants.STRING);
            
            System.out.println("Ricerca completata");
            location= new Location(addressNameString, latitudeString, longitudeString);
            System.out.println(location.getName() + "......." + location.getLatitude() + " ......." + location.getLongitude());
                
    }
    
    public Location getLocation()
    {
        return location;
    }
    
}

