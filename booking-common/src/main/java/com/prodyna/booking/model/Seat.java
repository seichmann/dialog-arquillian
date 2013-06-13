package com.prodyna.booking.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "seatPerFlight", columnNames = {
        "aircraft", "name"}))
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "aircraft")
    private Aircraft aircraft;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    public Seat() {
        super();
    }

    public Seat(Aircraft aircraft, String name) {
        super();
        this.aircraft = aircraft;
        this.name = name;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Seat [aircraft=" + aircraft + ", name=" + name + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((aircraft == null) ? 0 : aircraft.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Seat other = (Seat) obj;
        if (aircraft == null) {
            if (other.aircraft != null)
                return false;
        } else if (!aircraft.equals(other.aircraft))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
