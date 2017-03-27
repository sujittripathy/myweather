package com.weather.app.myweather.repo.hsql;

import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<UserPref,Long> {
}
