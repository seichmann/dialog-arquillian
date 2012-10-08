package com.prodyna.booking;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("aircraft")
public interface AircraftService {
    
	@POST
	@Path("{aid}")
	void create(@PathParam("aid") String aid );
    
	@DELETE
	@Path("{aid}")
	void delete(@PathParam("aid") String aid );
    
	@GET
	List<String> list();
}
