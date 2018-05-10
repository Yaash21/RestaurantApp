package com.stackroute.controller;

import java.math.BigDecimal;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.RestaurantApplication;
import com.stackroute.domain.Restaurant;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestaurantApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class RestaurantControllerIT {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers1 = new HttpHeaders();
	HttpHeaders headers2 = new HttpHeaders();

	@Test
	public void testRetrieveStudentCourse() {
		Restaurant restaurant = new Restaurant();
		restaurant.setId(2);
		restaurant.setRestaurantName("echo2");
		restaurant.setRestaurantLocation("Bangalore");
		restaurant.setCostOfTwo(new BigDecimal(1100));

		HttpEntity<Restaurant> entity = new HttpEntity<Restaurant>(restaurant, headers2);

		restTemplate.exchange(createURLWithPort("/api/v1/restaurant"), HttpMethod.POST, entity, String.class);

		HttpEntity<String> entity1 = new HttpEntity<String>(null, headers1);

		ResponseEntity<String> response1 = restTemplate.exchange(createURLWithPort("/api/v1/restaurant/2"),
				HttpMethod.GET, entity1, String.class);

		String expected = "{id:2,restaurantName:echo2,restaurantLocation:Bangalore,costOfTwo:1100}";

		try {
			JSONAssert.assertEquals(expected, response1.getBody(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void addRestaurantTest() {

		Restaurant restaurant = new Restaurant();
		restaurant.setId(3);
		restaurant.setRestaurantName("Truffles");
		restaurant.setRestaurantLocation("Bangalore");
		restaurant.setCostOfTwo(new BigDecimal(20000));

		HttpEntity<Restaurant> entity = new HttpEntity<Restaurant>(restaurant, headers2);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/restaurant"),
				HttpMethod.POST, entity, String.class);
		String expected = "{id:3,restaurantName:Truffles,restaurantLocation:Bangalore,costOfTwo:20000}";

		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
