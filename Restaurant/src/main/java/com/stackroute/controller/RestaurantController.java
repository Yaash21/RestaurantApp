package com.stackroute.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.domain.Restaurant;
import com.stackroute.service.RestaurantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//Controller class with five methods, add Restaurant,delete Restaurant,searchById, searchByRestaurantName, findAll.
@RestController
@RequestMapping("/api/v1")
@Api(value = "onlinestore", description = "Operations pertaining to restaurants in Online Store")
@CrossOrigin
public class RestaurantController {

	RestaurantService restaurantService;

	@Autowired
	// @Qualifier("restaurantServiceImpl")
	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	// save method is used to add the restaurant with post mapping.

	@ApiOperation(value = "Add Restaurant to your favourite list ", response = ResponseEntity.class)
	@PostMapping("/restaurant")
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant addedRestaurant = restaurantService.addRestaurant(restaurant);
		return new ResponseEntity<Restaurant>(addedRestaurant, HttpStatus.CREATED);
		// System.out.println(ResponseEntity<Restaurant>.);

	}

	// Delete method is used to delete the restaurant with delete mapping
	@ApiOperation(value = "Delete Restaurant to your favourite list ", response = ResponseEntity.class)
	@DeleteMapping("/restaurant/{id}")
	public ResponseEntity<String> deleteRestaurant(@PathVariable("id") int restaurantId) {
		String delete = restaurantService.deleteRestaurant(restaurantId);
		return new ResponseEntity<String>("{\"message\":\"" + delete + "\"}", HttpStatus.OK);

	}

	@ApiOperation(value = "Search restaurant by providing restaurant id ", response = ResponseEntity.class)
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<Restaurant> searchById(@PathVariable int id) {
		// Restaurant restaurant;
		Restaurant searchRestaurant = restaurantService.searchById(id);
		return new ResponseEntity<Restaurant>(searchRestaurant, HttpStatus.OK);

	}

	@ApiOperation(value = "List of all available restaurants ", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(value = "/restaurant")
	public ResponseEntity<List<Restaurant>> findAllRestaurant() {
		List<Restaurant> listOfRestaurant = restaurantService.findAll();
		return new ResponseEntity<List<Restaurant>>(listOfRestaurant, HttpStatus.OK);

	}

	@ApiOperation(value = "Search restaurant by providing restaurant name ", response = ResponseEntity.class)
	@GetMapping(value = "/restaurant/restaurantname", params = "name")
	public ResponseEntity<Restaurant> searchByRestaurantName(@RequestParam("name") String restaurantName) {
		Restaurant searchByRestaurantName = restaurantService.findByRestaurantName(restaurantName);
		return new ResponseEntity<Restaurant>(searchByRestaurantName, HttpStatus.OK);

	}

}
