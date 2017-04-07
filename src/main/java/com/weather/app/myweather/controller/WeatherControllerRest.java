package com.weather.app.myweather.controller;

import java.util.ArrayList;
import java.util.List;
import com.weather.app.myweather.repo.mogodb.WeatherRepository;
import com.weather.app.myweather.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.weather.app.myweather.model.UserPrefDTO;
import com.weather.app.myweather.model.WeatherDTO;

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

	@Autowired
	WeatherService weatherService;
	
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
		String u = unit.equalsIgnoreCase("C")?"metric":"imperial";
		WeatherDTO weather = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q="
							+city+"&appid="+appId+"&units="+u, WeatherDTO.class);

		logger.info("url::" + "http://api.openweathermap.org/data/2.5/weather?q="
						+city+"&appid="+appId+"&units="+u);
		logger.info("weather by city response :: "+weather.getMain().getTemp()+", Wind:"+weather.getWeather().get(0).getMain());
		return weather;
	}

	@RequestMapping(value = "/v1/add/{city}", method = RequestMethod.POST)
	public void addPreference(@RequestBody WeatherDTO weatherDTO,@PathVariable("city") String city){
		weatherService.savePreference(city);
	}

	@RequestMapping("/v1/city/all")
	public ResponseEntity<List<UserPrefDTO>> getWeatherForPrefCities(){

		List<UserPrefDTO> userPrefDTO = new ArrayList<>();
		try{
			weatherRepository.findAll().forEach(userPref -> {
				UserPrefDTO p = new UserPrefDTO();
				p.setCity(userPref.getCity());
				WeatherDTO w = getWeatherByCity(userPref.getCity(),"imperial");
				p.setTemp(w.getMain().getTemp());
				p.setCondition(w.getWeather().get(0).getDescription());
				userPrefDTO.add(p);
			});
			return new ResponseEntity(userPrefDTO, HttpStatus.OK);

		}catch (Exception exception){
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/v1/delete/{city}", method = RequestMethod.DELETE)
	public void removeCity(@PathVariable("city") String city){
		weatherService.deletePreference(city);
		logger.info("city got deleted.."+city);
	}
}


