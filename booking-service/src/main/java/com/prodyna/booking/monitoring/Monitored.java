package com.prodyna.booking.monitoring;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface Monitored {
    // ok
}
