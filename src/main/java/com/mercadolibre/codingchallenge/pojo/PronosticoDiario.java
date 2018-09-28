package com.mercadolibre.codingchallenge.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad que modela tanto la tabla donde se persisten los pronósticos diarios
 * como la respuesta JSON del endpoint del clima. Está en castellano para ser
 * compatible con la respuesta JSON sin tener que manipular el objeto
 * manualmente
 * 
 * @author andres
 *
 */
@Entity
@Table(name = "PRONOSTICO_DIARIO")
public class PronosticoDiario {

	@Id
	private int dia;
	private String clima;

	public PronosticoDiario() {
	}

	public PronosticoDiario(int dia, String clima) {
		this.dia = dia;
		this.clima = clima;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}
	
	/**
	 * Para uso de debugging
	 */
	@Override
	public String toString() {
		return "{ día: " + dia + ", clima: " + clima + "}";
	}

}