package com.prodyna.booking;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Local
@Path("/seat")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SeatService {
	
	@POST
	@Path("/aircraft/{aid}/seat/{sid}")
    void create(@PathParam("aid") String aid, @PathParam("sid") String sid );
	
	@DELETE
	@Path("/aircraft/{aid}/seat/{sid}")
    void delete(@PathParam("aid") String aid, @PathParam("sid") String sid );
	
	@GET
	@Path("/aircraft/{aid}")
    List<String> list(@PathParam("aid") String aid );
}
