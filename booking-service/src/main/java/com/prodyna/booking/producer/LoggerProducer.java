package com.prodyna.booking.producer;

import javax.enterprise.inject.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggerProducer {
	
	@Produces
	public Logger produceLogger() {
		return LoggerFactory.getLogger("bla");
	}
}
