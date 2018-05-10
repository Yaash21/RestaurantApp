package com.stackroute.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Restaurant;
import com.stackroute.service.RestaurantService;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)

public class RestaurantControllerTests {

	Restaurant restaurant1;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private RestaurantService restaurantService;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void testFindAll() throws Exception {

		restaurant1 = new Restaurant();
		restaurant1.setId(1);
		restaurant1.setRestaurantName("Truffles");
		restaurant1.setRestaurantLocation("Bglr");
		restaurant1.setCostOfTwo(new BigDecimal(2000));

		Restaurant restaurant2 = new Restaurant();
		restaurant2.setId(2);
		restaurant2.setRestaurantName("Truffles1");
		restaurant2.setRestaurantLocation("Bglr1");
		restaurant2.setCostOfTwo(new BigDecimal(2100));

		List<Restaurant> allRestaurants = Arrays.asList(restaurant1, restaurant2);

		given(restaurantService.findAll()).willReturn(allRestaurants);

		mvc.perform(get("/api/v1/restaurant").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].restaurantName", is("Truffles")))
				.andExpect(jsonPath("$[0].restaurantLocation", is("Bglr")))
				.andExpect(jsonPath("$[0].costOfTwo", is(2000))).andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].restaurantName", is("Truffles1")))
				.andExpect(jsonPath("$[1].restaurantLocation", is("Bglr1")))
				.andExpect(jsonPath("$[1].costOfTwo", is(2100)));
	}

	@Test
	public void testAddRestaurant() throws Exception {
		restaurant1 = new Restaurant();
		restaurant1.setId(1);
		restaurant1.setRestaurantLocation("Bglr");
		restaurant1.setCostOfTwo(new BigDecimal(2000));
		restaurant1.setRestaurantName("Truffles");

		ObjectMapper mapper = new ObjectMapper();
		this.mvc.perform(post("/api/v1/restaurant").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(restaurant1)))
				.andExpect(status().isCreated()).andExpect(status().is(201));
	}

	@Test
	public void searchByIdTest() throws Exception {
		restaurant1 = new Restaurant();
		restaurant1.setRestaurantName("echo");
		restaurant1.setId(1);
		restaurant1.setRestaurantLocation("Bangalore");
		restaurant1.setCostOfTwo(new BigDecimal(1100));
		given(restaurantService.searchById(1)).willReturn(restaurant1);
		this.mvc.perform(get("/api/v1/restaurant/1")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.restaurantName", is("echo")))
				.andExpect(jsonPath("$.restaurantLocation", is("Bangalore")))
				.andExpect(jsonPath("$.costOfTwo", is(1100)));

	}

	@Test
	public void deleteRestaurntTest() throws Exception {
		restaurant1 = new Restaurant();
		restaurant1.setRestaurantName("echo");
		restaurant1.setId(1);
		restaurant1.setRestaurantLocation("Bangalore");
		restaurant1.setCostOfTwo(new BigDecimal(1100));
		String deleted = "restaurant deleted";
		given(restaurantService.deleteRestaurant(1)).willReturn(deleted);
		this.mvc.perform(delete("/api/v1/restaurant/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).equals("restaurant deleted");
	}

	@Test
	public void searchByNameTest() throws Exception {
		restaurant1 = new Restaurant();
		restaurant1.setRestaurantName("echo");
		restaurant1.setId(1);
		restaurant1.setRestaurantLocation("Bangalore");
		restaurant1.setCostOfTwo(new BigDecimal(1100));
		given(restaurantService.findByRestaurantName("echo")).willReturn(restaurant1);
		this.mvc.perform(get("/api/v1/restaurant/restaurantname?name=echo").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.restaurantName", is("echo")))
				.andExpect(jsonPath("$.restaurantLocation", is("Bangalore")))
				.andExpect(jsonPath("$.costOfTwo", is(1100)));

	}
}