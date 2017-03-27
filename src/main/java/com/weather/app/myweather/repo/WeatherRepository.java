package com.weather.app.myweather.repo;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeatherRepository extends MongoRepository<UserPref,String> {
}
