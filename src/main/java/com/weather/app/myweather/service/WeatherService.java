package com.weather.app.myweather.service;

import com.weather.app.myweather.repo.mogodb.WeatherRepository;
import com.weather.app.myweather.repo.mogodb.UserPref;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    UserPref userPref;

    public void savePreference(String city){
        logger.info("City requested to add to preference : "+ city);
        userPref.setCity(city);
        weatherRepository.save(userPref);
    }

    public void deletePreference(String city){
        userPref.setCity(city);
        weatherRepository.delete(userPref);
    }
}
