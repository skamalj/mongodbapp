package com.kamalsblog.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.kamalsblog.demo.EmployeeController.DuplicateEmployeeIDException;
import com.kamalsblog.demo.EmployeeController.EmployeeIDNotFoundException;
import com.kamalsblog.demo.EmployeeController.InvalidEmployeeDataException;

@ControllerAdvice
public class CommonExceptionHandler {
	
	@ResponseBody
	@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(DuplicateEmployeeIDException.class)
	String duplicateEmployeeIdHandler (DuplicateEmployeeIDException ex) {
		return ex.getMessage();
	}
	
	@ResponseBody
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmployeeIDNotFoundException.class)
	String EmployeeIDNotFoundHandler (EmployeeIDNotFoundException ex) {
		return ex.getMessage();
	}
	
	
	@ResponseBody
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidEmployeeDataException.class)
	String InvalidEmployeeDataHandler (InvalidEmployeeDataException ex) {
		return ex.getMessage();
	}
}
