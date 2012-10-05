/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prodyna.booking.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Flight implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "flightNumber")
	private String flightNumber;

	public Flight() {
		super();
	}

	public Flight(String flightNumber) {
		super();
		this.flightNumber = flightNumber;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + "]";
	}
}
