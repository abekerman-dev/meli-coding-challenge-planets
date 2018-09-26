package com.mercadolibre.codingchallenge.util;

import java.awt.geom.Point2D;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase de utilidad para imprimir objetos al debuggear
 * 
 * @author andres
 *
 */
public class StringUtil {

	public static final String NEW_LINE = System.getProperty("line.separator");
	public static final String TAB = "\t";
	
	private static final Logger log = LoggerFactory.getLogger(StringUtil.class);

	private StringUtil() {
	}

	public static String prettyPrintPoint2D(Point2D p) {
		return "(" + p.getX() + ", " + p.getY() + ")";
	}

	public static void prettyPrint(Object[] array) {
		StringBuilder stringifiedArray = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			stringifiedArray.append(array[i] + (i == array.length - 1 ? "" : ", "));
		}
		log.info(stringifiedArray.toString());
	}
}
