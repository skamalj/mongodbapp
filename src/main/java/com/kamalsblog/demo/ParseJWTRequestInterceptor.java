package com.kamalsblog.demo;

import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;



@Component
public class ParseJWTRequestInterceptor implements HandlerInterceptor{

	private static final Logger logger  = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Decoder decoder = Base64.getDecoder();
		String bearerToken = 	request.getHeader("Authorization");
		if (bearerToken == null ) 
			return true;
		String[] tokens = 	bearerToken.substring(7)
				.trim()
				.split("\\.");
		String header =  new String(decoder.decode(tokens[0]));
		String payload =  new String(decoder.decode(tokens[1]));
		logger.info(header + "." + payload);
		return true;
	}

}
