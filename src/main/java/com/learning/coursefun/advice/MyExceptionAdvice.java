package com.learning.coursefun.advice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.learning.coursefun.custom.exception.BadCredentialException;
import com.learning.coursefun.custom.exception.EmptyInputException;

@RestControllerAdvice
public class MyExceptionAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleInputException(EmptyInputException empIn) {
		return new ResponseEntity<String>("input is empty, kindly provide input", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
		return new ResponseEntity<String>("Not found for input, try something else", HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIlligalArgumentException() {
		return new ResponseEntity<String>("input is null, retry",HttpStatus.BAD_REQUEST);
	}

	
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)  {
//		// List<ObjectError> list=ex.getAllErrors();
//		Map<String, String> errorMp = new HashMap<>();
//		ex.getBindingResult().getFieldErrors().forEach(err -> {
//			errorMp.put(err.getField(), err.getDefaultMessage());
//		});
//		return new ResponseEntity<>(errorMp,HttpStatus.BAD_REQUEST);
//	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errorMp = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(err -> {
			errorMp.put(err.getField(), err.getDefaultMessage());
		});
		return handleExceptionInternal(ex, errorMp, headers, status, request);
	}
	

	@ExceptionHandler(BadCredentialException.class)
	public ResponseEntity<String> handleBadCredential(BadCredentialException ex) {
		return new ResponseEntity<String>("incorrect details, try again", HttpStatus.NOT_FOUND);
	}

}
