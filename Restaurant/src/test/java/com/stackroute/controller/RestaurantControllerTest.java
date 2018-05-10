package com.stackroute.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.stackroute.domain.Restaurant;
import com.stackroute.service.RestaurantService;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

	private RestaurantController restaurantController;

	@MockBean
	private RestaurantService restaurantService;

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setupMock() throws Exception {
		MockitoAnnotations.initMocks(this);
		restaurantController = new RestaurantController();
		restaurantController.setRestaurantService(restaurantService);

	}

	// String jsonStr =
	// "{\"restaurantName\":\"echo\",\"id\":\"1\",\"restaurantLocation\":\"Bangalore\",\"costOfTwo\":\"1100\"}";

	@Test
	public void searchByIdTest() throws Exception {
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantName("echo");
		restaurant.setId(1);
		restaurant.setRestaurantLocation("Bangalore");
		restaurant.setCostOfTwo(new BigDecimal(1100));

		// Arrange
		Mockito.when(restaurantService.searchById(1)).thenReturn(restaurant);
		// Act
		// RequestBuilder requestBuilder =
		// MockMvcRequestBuilders.get("/api/v1/restaurant/1");
		mockMvc.perform(get("/api/v1/restaurant/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.costOfTwo", is(1100)));

	}

	// @Test
	// public void addRestaurantTest() throws Exception {
	// Restaurant restaurant = new Restaurant();
	// restaurant.setRestaurantName("echo");
	// restaurant.setId(1);
	// restaurant.setRestaurantLocation("Bangalore");
	// restaurant.setCostOfTwo(new BigDecimal(1100));
	//
	// // Arrange
	// Mockito.when(restaurantService.addRestaurant(restaurant)).thenReturn(restaurant);
	// // Act
	// RequestBuilder requestBuilder =
	// MockMvcRequestBuilders.post("/api/v1/restaurant")
	// .accept(MediaType.APPLICATION_JSON).content(jsonStr).contentType(MediaType.APPLICATION_JSON);
	//
	// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	//
	// MockHttpServletResponse response = result.getResponse();
	// // Assert
	// assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	//
	// assertEquals("http://localhost:8080/api/v1/restaurant",
	// response.getHeader(HttpHeaders.LOCATION));
	//
	// }
}
