package com.nemo.service;

import com.nemo.dao.WeatherDAO;
import com.nemo.entity.Weather;
import com.nemo.entity.WeatherRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* Author D.Gurov
* Weather service, it calls OpenWeather service
 */
@Service
public class WeatherService {

	private static Logger logger = LoggerFactory.getLogger(WeatherService.class);

	@Value("${weather.request.cities}")
	private String weatherCities;

	@Autowired
	private WeatherDAO weatherDAO;

	@Autowired
	private OpenWeatherService openWeatherService;

	private String[] cities;

	@PostConstruct
	private void postConstruct() {
		cities = weatherCities.split("\\s*,\\s*");
	}

	public List<Weather> get() {
		logger.info("Getting stored weather");
		return weatherDAO.get();
	}

	/**
	 * Gets current weather from OpenWeather
	 */
	public List<Weather> getNew() {
		logger.info("Getting new weather");
		try {
			return getWeather();
		} catch (Exception e) {
			logger.error("Error taking weather from OpenWeather", e);
			return null;
		}
	}

	public Weather save(Weather weather) {
		return weatherDAO.save(weather);
	}


	/**
	 * calls amd stores new weather and periosically
	 */
	@Scheduled(cron = "${weather.cron.expression}")
	public void getWeatherScheduled() {
		logger.info("Cron Dynamic Task: Current Time - {}", LocalDateTime.now());
		try {
			getWeather();
		} catch (Exception e) {
			logger.error("Error taking weather from OpenWeather", e);
		}
	}

	/**
	 * Get weather fpr each city and store to the DB
	 * @return
	 */
	private List<Weather> getWeather() {

		List<Weather> result = new ArrayList<Weather>();
		for (String city : this.cities) {

			WeatherRequest request = new WeatherRequest(city);

			try {
				Weather we = openWeatherService.getWeather(request);
				Weather weather = save(we);
				logger.info("Getting current weather for " + request.getCity() + ": " + weather);
				result.add(weather);

			} catch (Exception e) {
				logger.error("Error taking weather from OpenWeather for city = " +
						city, e);
			}
		}

		return result;
	}
}