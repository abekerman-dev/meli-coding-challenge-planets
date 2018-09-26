package com.mercadolibre.codingchallenge.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtil {

	private DateUtil() {
	}

	/**
	 * @return la cantidad de días de acá a 10 años, incluyendo años bisiestos
	 */
	public static long tenYearsAsDays() {
		final LocalDate today = LocalDate.now();
		return ChronoUnit.DAYS.between(today, today.plusYears(10));
	}

}
