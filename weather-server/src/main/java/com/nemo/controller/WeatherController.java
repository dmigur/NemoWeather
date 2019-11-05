package com.nemo.controller;

import com.nemo.model.Response;
import com.nemo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Author D.Gurov
 * Controller class
 */
@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    /**
     * Gets Weather data from the database
     * @return
     */
    @GetMapping("/weather")
    public ResponseEntity<Response> getWeather() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(weatherService.get(), new Date()));
    }

    /**
     * Gets current weather from OpenWeather
     * @return
     */
    @GetMapping("/weather/now")
    public ResponseEntity<Response> getCurrentWeather() {

        return ResponseEntity.status(HttpStatus.OK).body(new Response(weatherService.getNew(), new Date()));
    }

}
