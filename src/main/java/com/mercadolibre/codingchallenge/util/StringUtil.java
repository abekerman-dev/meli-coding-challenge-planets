package com.mercadolibre.codingchallenge.util;

import java.awt.geom.Point2D;

/**
 * Clase de utilidad para imprimir objetos al debuggear. No es código "productivo"
 * 
 * @author andres
 *
 */
public class StringUtil {

	public static final String NEW_LINE = System.getProperty("line.separator");
	public static final String TAB = "\t";

	public static String prettyPrintPoint2D(Point2D p) {
		return "(" + p.getX() + ", " + p.getY() + ")";
	}

	public static void prettyPrint(Object[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + (i == array.length - 1 ? "" : ", "));
		}
		System.out.println();
	}
}
