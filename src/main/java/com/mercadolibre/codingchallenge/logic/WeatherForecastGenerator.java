package com.mercadolibre.codingchallenge.logic;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
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

	// La idea de este mapa es tener el registro de qué día hay qué condición para
	// poder calcular luego el día de máximo pico de lluvia. Este cálculo se hace "a
	// demanda" y no apenas se construye este objeto. Además, sólo guarda los
	// primeros 360 días y luego se reutiliza para calcular los totales en 10 años
	private final Map<Integer, WeatherCondition> weatherConditionByDay = new HashMap<>();

	/**
	 * Calcula la condición climática para cada día entre 1 y 360 (es decir, los
	 * días con diferentes combinaciones de planetas que determinan el clima)
	 */
	private WeatherForecastGenerator() {
		for (int day = 0; day < 360; day++) {
			weatherConditionByDay.put(day + 1, chainOfEvaluators.getWeatherConditionForDay(day + 1));
		}
	}

	/**
	 * Devuelve la condición climática del día pasado como parámetro
	 * 
	 * @param day debe ser mayor o igual que 1 (0 no es un día válido)
	 * @return
	 */
	public WeatherCondition getWeatherConditionForDay(int day) {
		return weatherConditionByDay.get((day + 1) % 360);
	}

	/**
	 * Genera el resumen del pronóstico para los próximos 10 años junto con el día
	 * con el máximo pico de lluvia
	 * 
	 * @return una instancia del resumen del pronóstico
	 */
	public WeatherForecastSummary getForecastSummary() {
		return new WeatherForecastSummary(createWeatherConditionCountTotal(), getMaxTrianglePerimeterDay());
	}

	/**
	 * Reutiliza el mapa de condiciones climáticas por día, multiplicando por 10 sus
	 * cantidades y luego sumando el resto de los días (10 años % 360)
	 * 
	 * @return
	 */
	private Map<WeatherCondition, Long> createWeatherConditionCountTotal() {
		Map<WeatherCondition, Long> weatherConditionCount = weatherConditionByDay.entrySet().stream()
				.map(Map.Entry::getValue).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		Map<WeatherCondition, Long> weatherConditionCountTotal = new EnumMap<>(WeatherCondition.class);
		weatherConditionCount.entrySet().stream()
				.forEach(entry -> weatherConditionCountTotal.put(entry.getKey(), entry.getValue() * 10));

		weatherConditionByDay.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
				.limit(TEN_YEARS_AS_DAYS % 360).forEach(entry -> weatherConditionCountTotal.put(entry.getValue(),
						weatherConditionCountTotal.get(entry.getValue()) + 1));

		return weatherConditionCountTotal;
	}

	private int getMaxTrianglePerimeterDay() {
		Stream<Integer> sortedRainyDays = weatherConditionByDay.entrySet().stream()
				.filter(entry -> WeatherCondition.RAIN.equals(entry.getValue())).map(Map.Entry::getKey).sorted();

		return sortedRainyDays
				.collect(Collectors.toMap(Function.identity(),
						day -> MathUtil.createTriangle(AstronomyUtil.getPlanetPositionsByDay(day)).getPerimeter()))
				.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
	}

}
