package com.stackroute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.stackroute.domain.Restaurant;
import com.stackroute.exception.RestaurantAlreadyExistException;
import com.stackroute.exception.RestaurantNotFound;
import com.stackroute.repositories.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	RestaurantRepository restaurantRepository;

	@Autowired
	public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}

	public Restaurant addRestaurant(Restaurant restaurant) {
		try {
			restaurantRepository.save(restaurant);

		} catch (DuplicateKeyException de) {
			throw new RestaurantAlreadyExistException(restaurant);
		}
		return restaurant;
	}

	public Restaurant searchById(int id) {
		Restaurant restaurant = restaurantRepository.findById(id);
		if (restaurant == null) {
			throw new RestaurantNotFound("Restaurant with " + id + " does not exist");
		}
		return restaurant;

	}

	public List<Restaurant> findAll() {

		List<Restaurant> listOfRestaurants = restaurantRepository.findAll();
		if (listOfRestaurants.isEmpty()) {
			throw new RestaurantNotFound("There are no Resaturants in your Wishlist");
		}
		return listOfRestaurants;

	}

	public String deleteRestaurant(int id) {
		Restaurant restaurant = restaurantRepository.findById(id);
		if (restaurant == null) {
			throw new RestaurantNotFound("The Restaurant with " + id + " does not exist");
		}
		restaurantRepository.delete(id);
		return "restaurant deleted";

	}

	public Restaurant findByRestaurantName(String restaurantName) {
		Restaurant restaurant = restaurantRepository.findByRestaurantName(restaurantName);
		if (restaurant == null) {
			throw new RestaurantNotFound(restaurantName + " does not exist");
		}
		return restaurant;

	}

	public RestaurantRepository getRestaurantRepository() {
		return restaurantRepository;
	}

}
