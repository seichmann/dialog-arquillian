package com.prodyna.booking;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public interface SeatService {
	
	@POST
	@Path("/aircraft/{aid}/seat/{sid}")
    void create(@PathParam("aid") String aid, @PathParam("sid") String sid );
	
	@DELETE
	@Path("/aircraft/{aid}/seat/{sid}")
    void delete( String aid, String sid );
	
	@GET
	@Path("/aircraft")
    List<String> list( String aid );
}
