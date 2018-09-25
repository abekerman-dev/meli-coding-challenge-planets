package com.mercadolibre.codingchallenge.debugging;
//package com.mercadolibre.codingchallenge.tests;
//
//import java.util.List;
//
//import org.junit.Test;
//
//import com.mercadolibre.codingchallenge.model.pojo.Planet;
//import com.mercadolibre.codingchallenge.util.AstronomyUtil;
//import com.mercadolibre.codingchallenge.util.StringUtil;
//
//public class GalaxyTest {
//
//	@Test
//	public void test() {
//		System.out.println("Day #" + "\t" + "F" + "\t\t\t" + "V" + "\t\t\t" + "B");
//		List<Planet> planets = AstronomyUtil.getPlanetsInFarawayGalaxy();
//		for (int day = 0; day < 360; day++) {
//			int angleF = planets.get(0).getRotationAngleByDay(day);
//			int angleV = planets.get(1).getRotationAngleByDay(day);
//			int angleB = planets.get(2).getRotationAngleByDay(day);
//			if (angleF % 90 == 0 || angleV % 90 == 0 || angleB % 90 == 0) {
//				String posF = StringUtil.prettyPrintPoint2D(planets.get(0).getPositionByDay(day));
//				String posV = StringUtil.prettyPrintPoint2D(planets.get(1).getPositionByDay(day));
//				String posB = StringUtil.prettyPrintPoint2D(planets.get(2).getPositionByDay(day));
//				String _angleF = "\t" + (angleF % 90 == 0 ? angleF + " -> " + posF : "") + "\t\t\t";
//				String _angleV = "\t" + (angleF % 90 == 0 ? angleV + " -> " + posV : "") + "\t\t\t";
//				String _angleB = "\t" + (angleB % 90 == 0 ? angleB + " -> " + posB : "") + "\t\t\t\n";
//				System.out.format("%02d %s %s %s", day, _angleF, _angleV, _angleB);
//			}
//		}
//
//	}
//
//}
