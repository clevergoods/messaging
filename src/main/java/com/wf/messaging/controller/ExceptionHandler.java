package com.wf.messaging.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler({ SQLException.class, DataAccessException.class, PersistenceException.class })
	public ResponseEntity<String> databaseError(HttpServletRequest req, Exception ex) {
		log.error("Request: " + req.getRequestURL() + " raised " + ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getLocalizedMessage());
	}

	@org.springframework.web.bind.annotation.ExceptionHandler({ MethodArgumentNotValidException.class})
	public ResponseEntity<String> argumentError(HttpServletRequest req, MethodArgumentNotValidException ex) {
		log.error("Request: " + req.getRequestURL() + " raised " + ex);
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getLocalizedMessage());
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleFileNotFoundError(HttpServletRequest req, RuntimeException ex) {
		log.error("Request: " + req.getRequestURL() + " raised " + ex);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getLocalizedMessage());
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleError(HttpServletRequest req, Exception ex) {
		log.error("Request: " + req.getRequestURL() + " raised " + ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getLocalizedMessage());
	}
}
