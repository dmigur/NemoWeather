package com.nemo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Author D.Gurov
 * Weather entity class
 */
@Entity
@Table(name = "weather")
public class Weather implements Serializable {

    private static final long serialVersionUID = 1681261145191719508L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Number temp;

    private Number pressure;

    private Number humidity;

    private Number windSpeed;

    private String country;

    private String city;

    private Calendar date;

    // Generate Getters and Setters...
    public int getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Number getTemp() {
        return temp;
    }

    public void setTemp(Number temp) {
        this.temp = temp;
    }

    public Number getPressure() {
        return pressure;
    }

    public void setPressure(Number pressure) {
        this.pressure = pressure;
    }

    public Number getHumidity() {
        return humidity;
    }

    public void setHumidity(Number humidity) {
        this.humidity = humidity;
    }

    public Number getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Number windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", date=" + date +
                '}';
    }
}
