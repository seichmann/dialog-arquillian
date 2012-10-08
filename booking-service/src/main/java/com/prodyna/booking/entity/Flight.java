/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prodyna.booking.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Flight implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "flightNumber")
	private String flightNumber;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Aircraft aircraft;

	public Flight() {
		super();
	}

	public Flight(Aircraft aircraft, String flightNumber) {
		super();
		this.aircraft = aircraft;
		this.flightNumber = flightNumber;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + ", aircraft="
				+ aircraft + "]";
	}
}
