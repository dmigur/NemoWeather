package com.nemo.dao;

import com.nemo.entity.Weather;
import com.nemo.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author D.Gurov
 * DAO class
 */
@Repository
public class WeatherDAO {

    @Autowired
    private WeatherRepository weatherRepository;

    public List<Weather> get() {
        return weatherRepository.findAll(sortByDateDesc());
    }

    public Weather save(Weather weather) {
        return weatherRepository.save(weather);
    }

    private Sort sortByDateDesc() {
        return new Sort(Sort.Direction.DESC, "date");
    }

}
