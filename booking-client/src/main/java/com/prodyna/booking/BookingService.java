package com.prodyna.booking;

import com.prodyna.booking.model.Booking;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("bs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface BookingService {

    @POST
    @Path("booking")
    String book(@QueryParam("fid") String fid, @QueryParam("sid") String sid, @QueryParam("pax") String pax);

    @DELETE
    @Path("booking/{tid}")
    void cancel(@PathParam("tid") String tid);

    @GET
    @Path("booking")
    List<Booking> list();

    @GET
    @Path("booking/{tid}/flightnumber")
    String flightNumber(@PathParam("tid") String tid);

    @GET
    @Path("booking/{tid}/aircraft")
    String aircraft(@PathParam("tid") String tid);
}

