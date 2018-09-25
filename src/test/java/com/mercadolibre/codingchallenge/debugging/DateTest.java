package com.mercadolibre.codingchallenge.debugging;

import org.junit.Test;

import com.mercadolibre.codingchallenge.util.DateUtil;

/**
 * @deprecated
 * @author andres
 *
 */
public class DateTest {

	@Test
	public void test() {
		long tenYearsAsDays = DateUtil.tenYearsAsDays();
		System.out.format("tenYearsAsDays=%d, daysToMultiplyWeatherBy=%d, remainder=%d ", tenYearsAsDays, tenYearsAsDays / 360, tenYearsAsDays % 360);
	}

}
