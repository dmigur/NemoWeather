package com.nemo.exceptions;

/**
 * Author D.Gurov
 * exception class
 */
public class WeatherException extends Exception {

    public WeatherException(String s){
        super(s);
    }

    public WeatherException(String s, Exception e){
        super(s, e);
    }
}
