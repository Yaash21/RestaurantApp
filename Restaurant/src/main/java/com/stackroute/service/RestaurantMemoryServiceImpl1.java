package com.stackroute.service;

import java.util.ArrayList;
import java.util.List;

import com.stackroute.domain.Restaurant;

//@Service
//public class RestaurantMemoryServiceImpl implements RestaurantService {
public class RestaurantMemoryServiceImpl1 {	
	List<Restaurant> restaurants = new ArrayList<>();

	public Restaurant addRestaurant(Restaurant restaurant) {
		restaurants.add(restaurant);
		return restaurant;
	}

	public Restaurant searchById(int restaurantId) {
		return null;

	}

	public List<Restaurant> findAll() {
		return this.restaurants;

	}

	public String deleteRestaurant(int restaurantId) {
		return null;

	}

	public Restaurant findByRestaurantName(String restaurantName) {
		return null;
	}

}
