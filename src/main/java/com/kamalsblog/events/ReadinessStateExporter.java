package com.kamalsblog.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kamalsblog.demo.DemoApplication;

@Component
public class ReadinessStateExporter {	
	@Value("${greeting}")
	String greeting;
	
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	
	@EventListener
	public void onStateChange(AvailabilityChangeEvent<ReadinessState> event) {
		switch (event.getState()) {
		case ACCEPTING_TRAFFIC:
			logger.info("Accepting Traffic");
			logger.info(greeting);
			break;
		case REFUSING_TRAFFIC:
			logger.info("Refusing Traffic");
			break;
		}
		
	}

}
