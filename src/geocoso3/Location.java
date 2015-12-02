/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geocoso3;

import java.awt.Point;

/**
 *
 * @author alessandro
 */


/**
 * 
 *al the information about address and location of the place we are searching will be stored in an instance of this class.
 */
public class Location {
    private String name;
    private double latitude, longitude;
    
    /**
     *
     * @param n
     */
    public Location(String n)
    {
        name=n;
    }
    
    /**
     *
     * @param name
     * @param latitude
     * @param longitude
     */
    public Location(String name, String latitude, String longitude){
        this.name=name;
        this.longitude = Double.parseDouble(longitude);
        this.latitude = Double.parseDouble(latitude);
    }

    /**
     *
     * @return
     */
    public String getName(){
        return name;
    }
    
    /**
     *
     * @param n
     */
    public void setName(String n){
        name=n;
    }

    /**
     *
     * @return
     */
    public Double getLatitude(){
        return latitude;
    }
    
    /**
     *
     * @return
     */
    public double getLongitude(){
        return longitude;
    }
            
    /**
     *
     * @param coordinates
     */
    public void setCoordinates(Point.Double coordinates) {
        latitude = coordinates.getX();
        longitude = coordinates.getY();
    }
}