package com.prodyna.booking.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Aircraft implements Serializable {

    private static final long serialVersionUID = 1L;

    public Aircraft() {
        super();
    }

    public Aircraft(String registration) {
        super();
        this.registration = registration;
    }

    @Id
    @NotNull
    private String registration;

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "Aircraft [registration=" + registration + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((registration == null) ? 0 : registration.hashCode());
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
        Aircraft other = (Aircraft) obj;
        if (registration == null) {
            if (other.registration != null)
                return false;
        } else if (!registration.equals(other.registration))
            return false;
        return true;
    }
}
