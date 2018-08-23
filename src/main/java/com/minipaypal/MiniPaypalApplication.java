package com.minipaypal;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class MiniPaypalApplication extends ResourceConfig {

    public MiniPaypalApplication() {
        // Register resources and providers using package-scanning.
        packages("com.minipaypal.api");

        // Register an instance of LoggingFilter.
        //register(new LoggingFilter(LOGGER, true));

        // Enable Tracing support.
        //property(ServerProperties.TRACING, "ALL");
    }
}