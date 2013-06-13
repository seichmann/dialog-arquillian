package com.prodyna.booking.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "seatPerFlight", columnNames = {
        "flight", "seat"}))
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    public Booking() {
        super();
    }

    public Booking(Flight flight, Seat seat, String pax) {
        super();
        this.flight = flight;
        this.seat = seat;
        this.pax = pax;
    }

    public Booking(String ticket, Flight flight, Seat seat, String pax) {
        super();
        this.ticket = ticket;
        this.flight = flight;
        this.seat = seat;
        this.pax = pax;
    }

    @Id
    @NotNull
    @Column(name = "ticket")
    @Size(max = 2)
    private String ticket;

    @ManyToOne(optional = false)
    @JoinColumn(name = "flight")
    private Flight flight;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seat")
    private Seat seat;

    @Column(nullable = false)
    private String pax;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getPax() {
        return pax;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }

    @Override
    public String toString() {
        return "Booking [flight=" + flight + ", seat=" + seat + ", pax=" + pax
                + ", ticket=" + ticket + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((flight == null) ? 0 : flight.hashCode());
        result = prime * result + ((pax == null) ? 0 : pax.hashCode());
        result = prime * result + ((seat == null) ? 0 : seat.hashCode());
        result = prime * result + ((ticket == null) ? 0 : ticket.hashCode());
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
        Booking other = (Booking) obj;
        if (flight == null) {
            if (other.flight != null)
                return false;
        } else if (!flight.equals(other.flight))
            return false;
        if (pax == null) {
            if (other.pax != null)
                return false;
        } else if (!pax.equals(other.pax))
            return false;
        if (seat == null) {
            if (other.seat != null)
                return false;
        } else if (!seat.equals(other.seat))
            return false;
        if (ticket == null) {
            if (other.ticket != null)
                return false;
        } else if (!ticket.equals(other.ticket))
            return false;
        return true;
    }
}
