package com.nemo;

import com.nemo.entity.Weather;
import com.nemo.model.Response;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WeatherTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {

        super.setUp();
    }

    @Test
    public void getWeatherNow() throws Exception {
        String uri = "/weather/now";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Response response  = super.mapFromJson(content, Response.class);
        List<Weather> weather = response.getData();
        assertTrue(weather.size() > 0);

    }

    @Test
    public void getWeather() throws Exception {

        getWeatherNow();

        String uri = "/weather";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Response response  = super.mapFromJson(content, Response.class);
        List<Weather> weather = response.getData();
        assertTrue(weather.size() > 0);

    }



}