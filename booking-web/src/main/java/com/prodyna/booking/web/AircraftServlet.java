package com.prodyna.booking.web;

import com.prodyna.booking.AircraftService;
import com.prodyna.booking.model.Aircraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/aircraft")
public class AircraftServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Logger log = LoggerFactory.getLogger(getClass());

    @EJB
    private AircraftService as;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        String reg = req.getParameter("registration");
        if (action.equals("delete")) {
            log.info("Deleting Registration " + reg);
            as.delete(reg);
        } else if (action.equals("add")) {
            log.info("Creating Registration " + reg);
            as.create(reg);
        } else {
            // egal...
        }
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.print("<h1>Add aircraft</h1>");
        pw.print("<form id=\"addform\" method=\"POST\"><input type=\"text\" name=\"registration\"/><input type=\"hidden\" name=\"action\" value=\"add\"/><input type=\"submit\" value=\"Add\"/></form>");
        pw.print("<h1>Aircrafts</h1><ul>");
        for (Aircraft a : as.list()) {
            pw.print("<li>"
                    + a.getRegistration()
                    + "<form method=\"POST\"><input type=\"hidden\" name=\"registration\" value=\""
                    + a.getRegistration()
                    + "\"/><input type=\"hidden\" name=\"action\" value=\"delete\"/><input type=\"submit\" value=\"Remove\"/></form></li>");
        }
        pw.print("</ul>");
        pw.close();
    }

}
