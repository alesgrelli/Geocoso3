/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geocoso3;

/**
 *
 * @author alessandro
 */
public class Weather {
    private final Double temperature;// temperatureMin, temperatureMax;
    private final String humidity;
    private final String pressure; 
    private final String wind; 
    private final String description;
    private final String icon;
    
    /**
     * I need temperature, temperatureMin and temperatureMax to be converted into Double by casting.
     * @param temperature
     
     * @param humidity
     * @param pressure
     * @param wind
     * @param description 
     */
    
    public Weather(String temperature, String humidity, String pressure, String wind, String description, String icon) {
        this.temperature = toCelsius(Double.parseDouble(temperature));
        
        this.humidity = humidity;
        this.pressure = pressure;
        this.wind = wind;
        this.description = description;
        this.icon=icon;
    }
    
    /**
     * Temperatures will be registred as Farenheight, so I need to convert them as Celsius so that they can fit the European standards.
     * @param input
     * @return 
     */
    
    private Double toCelsius(Double input) {
        return input - 273.15;
    }
    
    /*public String getMaxTemperature(){
        return "" + temperatureMax.shortValue() + " °C";
    }
    
    
    public String getMinTemperature(){
        return "" + temperatureMin.shortValue() + " °C";
    }*/
    
    /**
     * Returns the actual temperature.
     *@return String
     */
    public String getTemperature() {
        return "" + temperature.shortValue() + " °C";
    }

    /**
     * Returns the humidity as a percentage.
     *@return String
     */
    public String getHumidity() {
        return humidity + " %";
    }

    /**
     * Returns the pressure measured in hPa.
     * @return String
     */
    public String getPressure() {
        return pressure + " hPa";
    }

    /**
     * returns the windspeed in m/s^2.
     *@return String
     */
    public String getWind() {
        return wind;
    }

    /**
     * Returns a description of the weather in order to know if it's sunny, coudy,...
     *@return String
     */
    public String getDescription() {
        return description;
    }
    
    public String getIcon()
    {
        return icon;
    }

    @Override
    public String toString()
    {
        String s = (this.getTemperature()+ "..." + this.getHumidity()+ "..." + 
                this.getPressure()+ "..." + this.getWind()+ "..." + this.getDescription());
        return s;
    }
    
}

