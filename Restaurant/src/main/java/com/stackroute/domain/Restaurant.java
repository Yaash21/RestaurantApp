package com.stackroute.domain;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document
@CompoundIndex(def = "{'restaurantName':1, 'restaurantLocation':1}", unique = true, name = "compound_index")
public class Restaurant {

	// @NotNull
	// @Size(min = 2, message = "Name should have atleast 2 characters")
	@ApiModelProperty(notes = "The Restaurant Name")
	private String restaurantName;

	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// to understand above line
	@Id
	@ApiModelProperty(notes = "The database generated product ID")
	private int id;
	//
	// @NotNull
	// @Size(min = 2, message = "Location should have atleast 2 characters")
	@ApiModelProperty(notes = "The Restaurant Location")
	private String restaurantLocation;

	@NotNull
	@ApiModelProperty(notes = "The cost of two in a restaurant	D")
	private BigDecimal costOfTwo;

	//
	// public Restaurant(String restaurantName, int id, String
	// restaurantLocation, BigDecimal costOfTwo) {
	// this.restaurantName = restaurantName;
	// this.id = id;
	// this.restaurantLocation = restaurantLocation;
	// this.costOfTwo = costOfTwo;
	// }

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantLocation() {
		return restaurantLocation;
	}

	public void setRestaurantLocation(String restaurantLocation) {
		this.restaurantLocation = restaurantLocation;
	}

	public BigDecimal getCostOfTwo() {
		return costOfTwo;
	}

	public void setCostOfTwo(BigDecimal costOfTwo) {
		this.costOfTwo = costOfTwo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((costOfTwo == null) ? 0 : costOfTwo.hashCode());
		result = prime * result + id;
		result = prime * result + ((restaurantLocation == null) ? 0 : restaurantLocation.hashCode());
		result = prime * result + ((restaurantName == null) ? 0 : restaurantName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		if (costOfTwo == null) {
			if (other.costOfTwo != null)
				return false;
		} else if (!costOfTwo.equals(other.costOfTwo))
			return false;
		if (id != other.id)
			return false;
		if (restaurantLocation == null) {
			if (other.restaurantLocation != null)
				return false;
		} else if (!restaurantLocation.equals(other.restaurantLocation))
			return false;
		if (restaurantName == null) {
			if (other.restaurantName != null)
				return false;
		} else if (!restaurantName.equals(other.restaurantName))
			return false;
		return true;
	}

}
