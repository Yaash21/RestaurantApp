package com.stackroute.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.domain.Restaurant;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, Integer> {

	public Restaurant findByRestaurantName(String restaurantName);

	public Restaurant findById(int id);
}
