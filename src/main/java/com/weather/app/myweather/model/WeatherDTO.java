package com.weather.app.myweather.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDTO {

	private Coord coord;
	private Main main;
	private String name;
	private Sys sys;
	private List<Weather> weather;
	private Wind wind;


	public WeatherDTO() {

	}

	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public List<Weather> getWeather() {
		return weather;
	}

	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	class Coord {
		private float lon;
		private float lat;

		public Coord() {

		}

		public float getLon() {
			return lon;
		}

		public void setLon(int lon) {
			this.lon = lon;
		}

		public float getLat() {
			return lat;
		}

		public void setLat(float lat) {
			this.lat = lat;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Main {
		private final float temp;
		private final float temp_min;
		private final float temp_max;
		private final float pressure;
		private final float humidity;

		@JsonCreator
		public Main(@JsonProperty("temp") float temp,
					@JsonProperty("temp_min") float temp_min,
					@JsonProperty("temp_max") float temp_max,
					@JsonProperty("pressure") float pressure,
					@JsonProperty("humidity")float humidity) {
			this.temp = temp;
			this.temp_min = temp_min;
			this.temp_max = temp_max;
			this.pressure = pressure;
			this.humidity = humidity;
		}

		public float getTemp() {
			return temp;
		}

		public float getTemp_min() {
			return temp_min;
		}

		public float getTemp_max() {
			return temp_max;
		}

		public float getPressure() {
			return pressure;
		}

		public float getHumidity() {
			return humidity;
		}
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	class Sys {
		private String country;

		public Sys(){
			
		}
		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Weather{
		private String id;
		private String main;
		private String description;
		private String icon;

		public Weather() {
		}

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getMain() {
			return main;
		}
		public void setMain(String main) {
			this.main = main;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Wind{
		private String speed;
		private String deg;

		public Wind() {
		}

		public String getSpeed() {
			return speed;
		}

		public void setSpeed(String speed) {
			this.speed = speed;
		}

		public String getDeg() {
			return deg;
		}

		public void setDeg(String deg) {
			this.deg = deg;
		}
	}
}
