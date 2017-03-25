package com.weather.app.myweather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.weather.app.myweather.model.WeatherDTO;

@RestController
@RequestMapping("/weather")
public class WeatherController {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
	
	@Value("${weather.api.key}")
	private String appId;
	
	@Value("${openweathermap.weather.endpoint}")
	private String weatherURL;
	 
	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	} 
	
	
	
	@RequestMapping("/v1/zip/{zip}/{unit}")
	public WeatherDTO getWeatherByZip(@PathVariable("zip") String zip,@PathVariable("unit") String unit){
	
		RestTemplate restTemplate = new RestTemplate();
		WeatherDTO weather = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?zip="
						+zip+"&appid="+appId+"&units="+unit, WeatherDTO.class);
		
		//logger.info("weather by zip code response : "+weather);
		return weather;
	}
	
	@RequestMapping("/v1/city/{city}/{unit}")
	public WeatherDTO getWeatherByCity(@PathVariable("city") String city,@PathVariable("unit") String unit){
		WeatherDTO weather = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q="
						+city+"&appid="+appId+"&units="+unit, WeatherDTO.class);
		logger.info("url::" + "http://api.openweathermap.org/data/2.5/weather?q="
						+city+"&appid="+appId+"&units="+unit);
		logger.info("weather by city response :: "+weather.getMain().getTemp()+", Wind:"+weather.getWeather().get(0).getMain());
		return weather;
	}
}
