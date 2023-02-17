package com.blog.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<Payload> userNotFoundExceptionHandler(UserNotFound userNotFound) {

		return ResponseEntity.badRequest()
				.body(new Payload(userNotFound.getClass().getName() + " " + userNotFound.getMessage(),
						HttpStatus.BAD_REQUEST, LocalDateTime.now().toString()));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Payload> globalHandler(HttpMessageNotReadableException exception) {
		return ResponseEntity.badRequest()
				.body(new Payload(exception.getClass().getName() + " " + exception.getMessage(), HttpStatus.BAD_REQUEST,
						LocalDateTime.now().toString()));
	}

//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<Payload> globalHandler(Exception exception) {
//		return ResponseEntity.badRequest()
//				.body(new Payload(exception.getClass().getName() + " " + exception.getMessage(), HttpStatus.BAD_REQUEST,
//						LocalDateTime.now().toString()));
//	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> validExceptionHandler(MethodArgumentNotValidException exception) {

		Map<String, String> map = new HashMap<>();

		exception.getBindingResult().getAllErrors().forEach(c -> {
			String field = ((FieldError) c).getField();
			String msg = c.getDefaultMessage();

			map.put(field, msg);
		});

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);

	}
}
