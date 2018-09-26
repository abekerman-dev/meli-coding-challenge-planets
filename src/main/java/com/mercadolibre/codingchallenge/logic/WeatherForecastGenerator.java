package com.mercadolibre.codingchallenge.logic;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mercadolibre.codingchallenge.enums.WeatherCondition;
import com.mercadolibre.codingchallenge.logic.chain.EvaluatorChainFactory;
import com.mercadolibre.codingchallenge.logic.chain.PlanetsPositionEvaluator;
import com.mercadolibre.codingchallenge.pojo.WeatherForecastSummary;
import com.mercadolibre.codingchallenge.util.AstronomyUtil;
import com.mercadolibre.codingchallenge.util.DateUtil;
import com.mercadolibre.codingchallenge.util.MathUtil;

/**
 * Calcula el pronóstico del tiempo para cada día de acá a los próximos 10 años
 * y genera un reporte con el resumen
 * 
 * @author andres
 *
 */
@Component
@Scope(value = "singleton")
public class WeatherForecastGenerator {

	private static final PlanetsPositionEvaluator chainOfEvaluators = EvaluatorChainFactory.getChain();
	private static final long TEN_YEARS_AS_DAYS = DateUtil.tenYearsAsDays();

	// Indico el initial capacity al construirlo ya que sé que voy a necesitar
	// exactamente esa cantidad de posiciones y no más
	// Y evito así el resizing en runtime
	private final List<WeatherCondition> weatherConditionsFor360Days = new ArrayList<>(360);

	private double maxTrianglePerimeter = 0;
	private int maxTrianglePerimeterDay = 0;

	private WeatherForecastGenerator() {
		init();
	}

	/**
	 * Devuelve la condición climática del día pasado como parámetro
	 * 
	 * @param day debe ser mayor o igual que 1 (0 no es un día válido)
	 * @return
	 */
	public WeatherCondition getWeatherConditionForDay(int day) {
		return weatherConditionsFor360Days.get((day - 1) % 360);
	}

	/**
	 * Genera el resumen del pronóstico para los próximos 10 años junto con el día
	 * con el pico de lluvia. Reutiliza la lista de condiciones climáticas para los
	 * 360 días ya calculada y la usa para contar el total de días con cada clima
	 * 
	 * @return una instancia del resumen del pronóstico
	 */
	public WeatherForecastSummary getForecastSummary() {
		Map<WeatherCondition, Long> weatherConditionCountFor360Days = createWeatherConditionCountMap(
				weatherConditionsFor360Days.stream());

		Map<WeatherCondition, Long> weatherConditionCountForRemainingDays = createWeatherConditionCountMap(
				weatherConditionsFor360Days.stream().limit(TEN_YEARS_AS_DAYS % 360));

		Map<WeatherCondition, Long> weatherConditionCountTotal = new EnumMap<>(WeatherCondition.class);

		weatherConditionCountFor360Days.forEach((weatherCondition, count) -> weatherConditionCountTotal
				.put(weatherCondition, count * (TEN_YEARS_AS_DAYS / 360)));

		weatherConditionCountForRemainingDays.forEach((weatherCondition, count) -> weatherConditionCountTotal
				.put(weatherCondition, weatherConditionCountTotal.get(weatherCondition) + count));

		return new WeatherForecastSummary(weatherConditionCountTotal, maxTrianglePerimeterDay);
	}

	/**
	 * Calcula, por un lado, la condición climática para cada día entre 1 y 360 (es
	 * decir, los días con diferentes combinaciones de planetas que determinan el
	 * clima) y por el otro el día con el pico de lluvia de esos 360
	 */
	private void init() {
		for (int day = 0; day < 360; day++) {
			WeatherCondition weatherCondition = chainOfEvaluators.getWeatherConditionForDay(day + 1);
			weatherConditionsFor360Days.add(weatherCondition);
			if (WeatherCondition.RAIN.equals(weatherCondition)) {
				calculateMaxRainDay(day + 1);
			}
		}
	}

	private void calculateMaxRainDay(int day) {
		double trianglePerimeterForDay = MathUtil.createTriangle(AstronomyUtil.getPlanetPositionsByDay(day))
				.getPerimeter();
		if (trianglePerimeterForDay > maxTrianglePerimeter) {
			maxTrianglePerimeter = trianglePerimeterForDay;
			maxTrianglePerimeterDay = day;
		}
	}

	private Map<WeatherCondition, Long> createWeatherConditionCountMap(Stream<WeatherCondition> startingStream) {
		return startingStream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}

}
