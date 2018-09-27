package com.mercadolibre.codingchallenge.pojo;

public class DayWeatherPair {
	
	private int day;
	private String weatherCondition;

	public DayWeatherPair(int day, String weatherCondition) {
		this.day = day;
		this.weatherCondition = weatherCondition;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getWeatherCondition() {
		return weatherCondition;
	}

	public void setWeatherCondition(String weatherCondition) {
		this.weatherCondition = weatherCondition;
	}
	
}