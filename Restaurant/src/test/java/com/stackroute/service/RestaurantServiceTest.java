// package com.stackroute.service;
//
// import static org.hamcrest.CoreMatchers.equalTo;
// import static org.hamcrest.CoreMatchers.is;
// import static org.junit.Assert.assertThat;
// import static org.mockito.Mockito.when;
//
// import java.util.List;
//
// import org.junit.Before;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.mockito.runners.MockitoJUnitRunner;
//
//
//
// @RunWith(MockitoJUnitRunner.class)
// public class RestaurantServiceTest {
// @Mock
// private RestaurantServiceImpl restaurantServiceImpl;
// @Mock
// private RestaurantRepository restaurantRepository;
// @Mock
// private Restaurant restaurant;
// @Mock
// private List<Restaurant> restaurants;
//
// @Before
// public void setupMock() {
// MockitoAnnotations.initMocks(this);
// restaurantServiceImpl = new RestaurantServiceImpl();
// restaurantServiceImpl.setRestaurantRepository(restaurantRepository);
// }
//
// @Test
// public void findByIdTest() throws Exception {
// when(restaurantRepository.findById(2)).thenReturn(restaurant);
// // Act
// Restaurant retrievedRestaurant = restaurantServiceImpl.searchById(2);
// // Assert
// assertThat(retrievedRestaurant, is(equalTo(restaurant)));
// }
//
// @Test
// public void findAllTest() throws Exception {
// when(restaurantRepository.findAll()).thenReturn(restaurants);
// // Act
// List<Restaurant> retrievedRestaurants = restaurantServiceImpl.findAll();
// // Assert
// assertThat(retrievedRestaurants, is(equalTo(restaurants)));
// }
//
// @Test
// public void findByRestaurantIdTest() throws Exception {
// when(restaurantRepository.findByRestaurantName("Truffles")).thenReturn(restaurant);
// // Act
// Restaurant retrievedRestaurant =
// restaurantServiceImpl.findByRestaurantName("Truffles");
// // Assert
// assertThat(retrievedRestaurant, is(equalTo(restaurant)));
// }
//
// @Test
// public void addRestaurantTest() throws Exception {
// // Arrange
// when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
// // Act
// Restaurant savedRestaurant = restaurantServiceImpl.addRestaurant(restaurant);
// // Assert
// assertThat(savedRestaurant, is(equalTo(restaurant)));
// }
//
// // @Test
// // public void deleteRestaurantTest() throws Exception {
// // // Arrange
// // doNothing().when(restaurantRepository).delete(2);
// // // RestaurantRepository my = Mockito.mock(RestaurantRepository.class);
// // // Act
// // String delete = restaurantServiceImpl.deleteRestaurant(2);
// // // Assert
// // assertThat(delete, is(equalTo("restaurant deleted")));
// // verify(restaurantRepository, times(1)).delete(2);
// // }
//
// }
