package com.stackroute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RestaurantNotFound extends RuntimeException {

	public RestaurantNotFound(String exception) {
		super(exception);
	}

}
