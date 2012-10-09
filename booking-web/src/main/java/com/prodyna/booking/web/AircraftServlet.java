package com.prodyna.booking.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prodyna.booking.AircraftService;

@WebServlet(urlPatterns="/aircraft")
public class AircraftServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private AircraftService as;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		pw.print("<h1>Aircrafts</h1><ul>");
		for( String a : as.list() ) {
			pw.print("<li>" + a + "</li>");
		}
		pw.print("</ul>");
		pw.close();
	}

	
}
