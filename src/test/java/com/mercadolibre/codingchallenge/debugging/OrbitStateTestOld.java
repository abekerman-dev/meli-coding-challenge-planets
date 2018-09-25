package com.mercadolibre.codingchallenge.debugging;
//package com.mercadolibre.codingchallenge.tests;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.junit.Test;
//
//import com.mercadolibre.codingchallenge.util.AstronomyUtil;
//
///**
// * @deprecated
// * @author andres
// *
// */
//public class OrbitStateTestOld {
//
//	@Test
//	public void test() {
//		for (int day = 0; day < 360; day++) {
//			// galaxy.calculateOrbitStateByDay(day);
//			final int aDay = day;
//			List<Integer> angles = 
//				AstronomyUtil.getPlanetsInFarawayGalaxy()
//				.stream()
//				.map(planet -> planet.getRotationAngleByDay(aDay))
//				.collect(Collectors.toList());
//			int angdegB = angles.get(0);
//			int angdegV = angles.get(1);
//			int angdegF = angles.get(2);
//			double tanB = Math.tan(Math.toRadians(angdegB));
//			double tanV = Math.tan(Math.toRadians(angdegV));
//			double tanF = Math.tan(Math.toRadians(angdegF));
//			if (tanB == tanV && tanV == tanF) {
//				System.out.format(
//						"For day=%03d -> " + "angdegB=%03d | tanB=%f, angdegV=%03d | tanV=%f, angdegF=%03d | tanF=%f\n",
//						day, angdegB, tanB, angdegV, tanV, angdegF, tanF);
//			}
//			
//		}
//	}
//
//}
