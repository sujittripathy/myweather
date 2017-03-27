package com.weather.app.myweather.controller;

import com.weather.app.myweather.model.UserPrefDTO;
import com.weather.app.myweather.model.WeatherDTO;
import com.weather.app.myweather.repo.UserPref;
import com.weather.app.myweather.repo.WeatherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class WeatherControllerRest {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherControllerRest.class);

	@Value("${weather.api.key}")
	private String appId;
	
	@Value("${openweathermap.weather.endpoint}")
	private String weatherURL;
	 
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WeatherRepository weatherRepository;

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
	public WeatherDTO getWeatherByCity(@PathVariable("city") String city, @PathVariable("unit") String unit){
		String u = unit.equalsIgnoreCase("C")?"celcius":"imperial";
		WeatherDTO weather = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q="
							+city+"&appid="+appId+"&units="+u, WeatherDTO.class);

		logger.info("url::" + "http://api.openweathermap.org/data/2.5/weather?q="
						+city+"&appid="+appId+"&units="+u);
		logger.info("weather by city response :: "+weather.getMain().getTemp()+", Wind:"+weather.getWeather().get(0).getMain());
		return weather;
	}

	@RequestMapping(value = "/v1/add/{city}", method = RequestMethod.POST)
	public void addPreference(@RequestBody WeatherDTO weatherDTO,@PathVariable("city") String city){
		UserPref up= new UserPref();
		up.setCity(city);
		weatherRepository.save(up);
	}

	@RequestMapping("/v1/city/all")
	public List<UserPrefDTO> getWeatherForPrefCities(){
		List<UserPrefDTO> userPrefDTO = new ArrayList<>();
		weatherRepository.findAll().forEach(userPref -> {
			UserPrefDTO p = new UserPrefDTO();
			p.setCity(userPref.getCity());
			WeatherDTO w = getWeatherByCity(userPref.getCity(),"imperial");
			p.setTemp(w.getMain().getTemp());
			p.setCondition(w.getWeather().get(0).getMain());
			p.setId(userPref.getId());
			userPrefDTO.add(p);
		});
		return userPrefDTO;
	}

	@RequestMapping(value = "/v1/delete/{id}", method = RequestMethod.DELETE)
	public void removeCity(@PathVariable("id") String id){
		weatherRepository.delete(id);
		logger.info("id deleted.."+id);
	}
}