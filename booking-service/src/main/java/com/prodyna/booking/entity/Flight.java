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
    @Column(name="flightNumber")
    private String flightNumber;
}
