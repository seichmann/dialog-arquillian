package com.prodyna.booking.monitoring;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;
import javax.interceptor.InterceptorBinding;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
@InterceptorBinding
public @interface Monitored {
	// ok
}
