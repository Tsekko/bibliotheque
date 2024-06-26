package com.mk.bibliotheque.security.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AccessDeniedHandlerCustom implements AccessDeniedHandler {
	private static final Logger logger = LoggerFactory.getLogger(AccessDeniedHandlerCustom.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		logger.error("Forbidden error: {}", accessDeniedException.getMessage());
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "Error: Access forbidden");
	}

}
