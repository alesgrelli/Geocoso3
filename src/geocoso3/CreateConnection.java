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
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.exit;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author alessandro
 */

//I need to create a class that can handle the internet connection and configuration of the proxy.
public class CreateConnection {

    /**
     *
     */
    public String urlUsed;
    private InputStream stream;
    private String proxyAddress="192.168.0.1", port="8080";
    private String username="19101229", password="Juventus1";
    private Boolean proxyStatus;
    
    /**
     *
     * @param url
     */
    public CreateConnection(String url){
        urlUsed=url;
        connect();
        
    }
    
    /**
     *
     * @return
     */
    public InputStream getConnection() {
        return stream;
    }
    
    //trying to connect to the internet
    private void connect() {
        try {
            prepareProxy();
            System.out.println(urlUsed);
            URL URLInput = new URL(urlUsed);
            HttpURLConnection ConnectionInput = (HttpURLConnection) URLInput.openConnection();
            System.out.println("connessione fatta.\nAttendere...");
            System.out.println(ConnectionInput.getResponseCode());
            stream = ConnectionInput.getInputStream();
            System.out.println("stream creato");
        } catch (MalformedURLException ex) {
            System.out.println("Conection problem, try again.");
            stream = null;
            exit(1);
        } catch (IOException ex) {
            System.out.println("errore in connessione");
            
            /*if (!proxyStatus) {
                System.out.println("Problem occurred, trying another proxy.");
                proxyStatus = true;
                prepareProxy();
                connect();
            } else {
                System.out.println("No internet connection, check it and try again.");
                exit(1);
            }*/
        }
    }
    
    //setting up the proxy configuration
    
    private void prepareProxy() {
        //getSetting();
        System.setProperty("http.proxyHost", proxyAddress);
        System.setProperty("http.proxyPort", port);
        Authenticator.setDefault(new Authenticator() {
                    @Override
                    public PasswordAuthentication getPasswordAuthentication() {
                        System.out.println("quel problema nn ce ");
                        return new PasswordAuthentication(
                                username, password.toCharArray());
                    }
                }
        );
        
        System.out.println("proxy applied");
    }
    
    //in case of manual setting, this method will be used
    
    private void getSetting() {
        try {
            try (BufferedReader br = new BufferedReader(new FileReader("proxyconfig.txt"))) {
                proxyAddress = br.readLine();
                port = br.readLine();
                username = br.readLine();
                password = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Connection failed!! No configuration file!" + "\n");
            exit(1);
        } catch (IOException ex) {
            System.out.println("No internet connection.");
        }
    }
}
