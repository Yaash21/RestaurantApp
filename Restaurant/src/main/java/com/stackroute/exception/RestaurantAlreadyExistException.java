package com.stackroute.exception;

import com.stackroute.domain.Restaurant;

public class RestaurantAlreadyExistException extends RuntimeException {

	public RestaurantAlreadyExistException(Restaurant restaurant) {
		super(restaurant.getRestaurantName() + " is already present");
	}
}
