package com.nemo.service;

import com.nemo.entity.Weather;
import com.nemo.entity.WeatherRequest;
import com.nemo.exceptions.WeatherException;
import com.nemo.util.Constants;
import com.nemo.util.JsonUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Calendar;

/**
 * Author: D.Gurov
 * OpenWeather service, gets weather frrom the openweathermap.org
 */
@Service
public class OpenWeatherService {

    @Value("${weather.url.template}")
    private String weatherByCityUrlTemplate;

    private static Logger logger = LoggerFactory.getLogger(OpenWeatherService.class);

    public OpenWeatherService() {

    }

    public Weather getWeather(WeatherRequest request) throws WeatherException {

        try {

          return processRequest(request);

        } catch (Exception e) {

            logger.error("error getting current weather", e);
            throw new WeatherException(e.getMessage());
        }
    }

    private Weather processRequest(WeatherRequest request) throws Exception {
        JSONObject obj = readJsonWeather(request);
        Weather weather = parseJsonWeather(obj);
        weather.setCity(request.getCity());
        return weather;
    }

    private JSONObject readJsonWeather(WeatherRequest request) throws Exception {
        String city = request.getCity();
        String url = null;
        url = weatherByCityUrlTemplate;
        url = url.replace("####", city);
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } catch (UnknownHostException e) {
            throw new WeatherException("Unknown host: " + url);
        } catch (Exception e) {
            throw new WeatherException("Error connecting to OpenWeather site: " + e.getMessage());
        } finally {
            is.close();
        }

    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private Weather parseJsonWeather(JSONObject json) throws Exception {

        try {
            JSONObject main = (JSONObject) JsonUtil.getValue(json, Constants.main.name());
            if (main == null) {
                throw new WeatherException("no weather data");
            }

            Weather weather = new Weather();
            Number temp = (Number) JsonUtil.getValue(main, Constants.temp.name());

            Number humidity = (Number) JsonUtil.getValue(main, Constants.humidity.name());
            JSONObject wind = (JSONObject) JsonUtil.getValue(json, Constants.wind.name());

            weather.setPressure((Number) JsonUtil.getValue(main, Constants.pressure.name()));
            weather.setHumidity(humidity);
            weather.setTemp(temp);
            weather.setDate(Calendar.getInstance());

            if (wind != null) {
                weather.setWindSpeed((Number) JsonUtil.getValue(wind, Constants.speed.name()));
            }

            JSONObject sys = (JSONObject) JsonUtil.getValue(json, Constants.sys.name());
            if (sys != null) {
                weather.setCountry((String) JsonUtil.getValue(sys, Constants.country.name()));
            }
            return weather;

        } catch (WeatherException e) {

            logger.error("error parsing weather", e);
            throw e;

        } catch (Exception e) {

            logger.error("error parsing weather", e);
            throw new WeatherException("Error parsing weather ");

        }

    }

}