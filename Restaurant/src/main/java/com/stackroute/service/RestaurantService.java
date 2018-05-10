package com.stackroute.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stackroute.domain.Restaurant;

@Service
public interface RestaurantService {

	public Restaurant addRestaurant(Restaurant restaurant);

	public Restaurant searchById(int id);

	public List<Restaurant> findAll();

	public String deleteRestaurant(int restaurantId);

	public Restaurant findByRestaurantName(String restaurantName);
}
