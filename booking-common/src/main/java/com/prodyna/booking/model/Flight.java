/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prodyna.booking.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "flightNumber")
    private String flightNumber;

    @ManyToOne
    @JoinColumn(nullable = false)
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
