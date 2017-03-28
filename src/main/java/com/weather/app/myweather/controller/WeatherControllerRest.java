package com.weather.app.myweather.controller;

import java.util.ArrayList;
import java.util.List;

/*import com.weather.app.myweather.repo.UserPref;
import com.weather.app.myweather.repo.WeatherRepository;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.weather.app.myweather.model.UserPrefDTO;
import com.weather.app.myweather.model.WeatherDTO;
import com.weather.app.myweather.repo.hsql.UserPref;
import com.weather.app.myweather.repo.hsql.WeatherRepository;

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
			p.setCondition(w.getWeather().get(0).getDescription());
			p.setId(String.valueOf(userPref.getId()));
			userPrefDTO.add(p);
		});
		return userPrefDTO;
	}

	@RequestMapping(value = "/v1/delete/{id}", method = RequestMethod.DELETE)
	public void removeCity(@PathVariable("id") String id){
		weatherRepository.delete(Long.valueOf(id));
		logger.info("id deleted.."+id);
	}


	// Just a dummy method for testing purpose
	@RequestMapping("/dummy")
	public Greetings getTest(){
		return new Greetings("Hello","There!");
	}
}


class Greetings{
	private String name;
	private String value;

	public Greetings(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}

}

