package com.rest.api;

import org.glassfish.jersey.server.ResourceConfig;

public class ConfigApp extends ResourceConfig {
	public ConfigApp() {
        packages("com.availableorders.restapp.AvailableOrders");
    }  
	
}
