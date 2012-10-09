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
@Path("fs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface FlightService {
    
	@POST
	@Path("flight/{fid}/aircraft/{aid}")
	void create(@PathParam("aid") String aid, @PathParam("fid") String fid );
    
	@DELETE
	@Path("flight/{fid}")
	void delete(@PathParam("fid") String fid );
    
	@GET
	@Path("flight")
	List<String> list();
}
