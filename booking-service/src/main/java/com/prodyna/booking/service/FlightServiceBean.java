package com.prodyna.booking.service;

import java.util.List;

import javax.ejb.Stateless;

import com.prodyna.booking.FlightService;

@Stateless
public class FlightServiceBean implements FlightService {

	@Override
	public void create(String aid, String fid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String fid) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
