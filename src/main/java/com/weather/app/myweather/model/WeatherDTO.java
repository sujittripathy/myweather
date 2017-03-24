package com.weather.app.myweather.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDTO {

	private Coord coord;
	private Main main;
	private String name;
	private Sys sys;
	private ArrayList<WeatherCondition> weatherCondition;
	
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

	public ArrayList<WeatherCondition> getWeatherCondition() {
		return weatherCondition;
	}

	@JsonCreator
	public void setWeatherCondition(ArrayList<WeatherCondition> weatherCondition) {
		this.weatherCondition = weatherCondition;
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
	public class Main {
		private int temp;
		private int temp_min;
		private int temp_max;

		public Main() {

		}

		public int getTemp() {
			return temp;
		}

		public void setTemp(int temp) {
			this.temp = temp;
		}

		public int getTemp_min() {
			return temp_min;
		}

		public void setTemp_min(int temp_min) {
			this.temp_min = temp_min;
		}

		public int getTemp_max() {
			return temp_max;
		}

		public void setTemp_max(int temp_max) {
			this.temp_max = temp_max;
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
	class WeatherCondition{
		private String id;
		private String main;
		private String description;
		private String icon;
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
}
