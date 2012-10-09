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
@Path("as")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface AircraftService {
    
	@POST
	@Path("aircraft/{aid}")
	void create(@PathParam("aid") String aid );
    
	@DELETE
	@Path("aircraft/{aid}")
	void delete(@PathParam("aid") String aid );
    
	@GET
	@Path("aircraft")
	List<String> list();
}

