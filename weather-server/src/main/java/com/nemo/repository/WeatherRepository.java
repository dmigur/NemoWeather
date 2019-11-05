package com.nemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nemo.entity.Weather;

/**
 * Author D.Gurov
 * Weather repository interace
 */
@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {

}
