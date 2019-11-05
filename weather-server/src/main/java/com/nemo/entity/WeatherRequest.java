package com.nemo.entity;

/**
 * Author D.Gurov
 * Weather request passed to OpenWeather
 */
public class WeatherRequest {
    private String city;

    public WeatherRequest(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}