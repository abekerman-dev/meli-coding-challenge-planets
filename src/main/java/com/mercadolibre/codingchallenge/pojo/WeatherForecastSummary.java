package com.mercadolibre.codingchallenge.pojo;

import java.util.Map;

import com.mercadolibre.codingchallenge.enums.WeatherCondition;
import com.mercadolibre.codingchallenge.util.StringUtil;

public class WeatherForecastSummary {

	private Map<WeatherCondition, Long> weatherConditionCount;
	private int maxRainDay;

	public WeatherForecastSummary(Map<WeatherCondition, Long> conditionCount, int maxRainDay) {
		this.weatherConditionCount = conditionCount;
		this.maxRainDay = maxRainDay;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Predicción climática para los próximos 10 años:");
		sb.append(StringUtil.NEW_LINE + StringUtil.NEW_LINE);
		weatherConditionCount.keySet().stream().sorted().forEach(
				k -> sb.append("\tPeríodos de " + k + ": " + weatherConditionCount.get(k) + StringUtil.NEW_LINE));
		sb.append(StringUtil.NEW_LINE);
		sb.append("\tEl día de pico máximo de lluvia será: ");
		sb.append(maxRainDay);

		return sb.toString();
	}

}
