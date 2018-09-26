package com.mercadolibre.codingchallenge.util;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.mercadolibre.codingchallenge.enums.RotationType;
import com.mercadolibre.codingchallenge.pojo.Planet;
import com.mercadolibre.codingchallenge.pojo.Triangle;

/**
 * Clase que permite a sus clientes abstraer el problema matemático o
 * trigonométrico de fondo al hablar en términos de "negocio" (astronómico en
 * este caso)
 * 
 * @author andres
 *
 */
public class AstronomyUtil {

	private static final Point SUN = new Point(0, 0);

	private static final List<Planet> PLANETS = Arrays.asList(
			Planet.getInstance("Ferengi", 1, RotationType.ROTATION_CLOCKWISE, 500),
			Planet.getInstance("Vulcano", 5, RotationType.ROTATION_COUNTERCLOCKWISE, 1000),
			Planet.getInstance("Betasoide", 3, RotationType.ROTATION_CLOCKWISE, 2000));

	private AstronomyUtil() {
	}

	public static List<Planet> getPlanets() {
		return PLANETS;
	}

	/**
	 * Determina si los planetas y el sol están alineados entre sí el día dado,
	 * primero si los planetas están sobre el eje Y (tangente infinita) y sino
	 * chequea que las tangentes de los planetas son las mismas
	 * 
	 * @param day
	 * @return true si los planetas y el sol están alineados entre sí el día dado
	 */
	public static boolean arePlanetsAndSunAligned(int day) {
		return MathUtil.areAllPointsOnYAxis(getPlanetPositionsByDay(day))
				|| MathUtil.tangentsAreEqual(getPlanetTangentsByDay(day));
	}

	/**
	 * Determina si los planetas están alineados en un día dado, pero asume que la
	 * recta que los uniría no pasa por el sol porque eso ya fue descartado al
	 * devolver false el método arePlanetsAndSunAligned. Antes de ejecutar el
	 * cálculo de la pendiente ordena los puntos según su posición en el plano
	 * 
	 * @param day
	 * @return true si los planetas están alineados según la pendiente de los
	 *         segmentos entre los primeros y los últimos dos puntos
	 */
	public static boolean arePlanetsButNotSunAligned(int day) {
		List<Point2D> planetPoints = getPlanetPositionsByDay(day);

		Point2D p1 = planetPoints.get(0);
		Point2D p2 = planetPoints.get(1);
		Point2D p3 = planetPoints.get(2);

		return MathUtil.arePointsAlignedBySlope(p1, p2, p3);
	}

	/**
	 * @param day
	 * @return true si los planetas forman un triángulo con el sol adentro en el día
	 *         dado
	 */
	public static boolean arePlanetsInTriangleWithSunInside(int day) {
		List<Point2D> planetPoints = getPlanetPositionsByDay(day);

		Point2D p1 = planetPoints.get(0);
		Point2D p2 = planetPoints.get(1);
		Point2D p3 = planetPoints.get(2);

		return new Triangle(p1, p2, p3).contains(SUN);
	}

	/**
	 * @param day
	 * @return una lista de puntos (x, y) ordenados por su posición en el plano en
	 *         el día dado
	 */
	public static List<Point2D> getPlanetPositionsByDay(int day) {
		return PLANETS.stream().map(planet -> planet.getPositionByDay(day)).sorted(ComparatorUtil.point2DComparator())
				.collect(Collectors.toList());
	}

	/**
	 * 
	 * @param day
	 * @return una lista con las tangentes (sin signo) de cada planeta para el día
	 *         dado
	 */
	private static List<Double> getPlanetTangentsByDay(int day) {
		return PLANETS.stream().map(planet -> planet.getUnsignedTangentByDay(day)).collect(Collectors.toList());
	}

}
