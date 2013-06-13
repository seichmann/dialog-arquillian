package com.prodyna.booking;

import com.prodyna.booking.model.Aircraft;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("as")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface AircraftService {

    @POST
    @Path("aircraft/{aid}")
    void create(@PathParam("aid") String aid);

    @DELETE
    @Path("aircraft/{aid}")
    void delete(@PathParam("aid") String aid);

    @GET
    @Path("aircraft")
    List<Aircraft> list();
}

