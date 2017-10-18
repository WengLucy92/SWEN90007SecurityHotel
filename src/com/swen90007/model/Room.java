package com.swen90007.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Room_NEW")
public class Room {

	private Long id;
	private String type;
	private int guest;
	private int price;
	private String description;
	private String[] services;
	private Boolean breakfast;
	private int[] left;
	
	@Id
	@GeneratedValue
	@Column(name="ROOM_ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="ROOM_TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Column(name="ROOM_GUEST")
	public int getGuest() {
		return guest;
	}
	public void setGuest(int guest) {
		this.guest = guest;
	}
	
	@Column(name="ROOM_PRICE")
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
		
	@Column(name="ROOM_DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="ROOM_SERVICES")
	public String[] getServices() {
		return services;
	}
	public void setServices(String[] services) {
		this.services = services;
	}
	
	@Column(name="ROOM_BREAKFAST")
	public Boolean getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(Boolean breakfast) {
		this.breakfast = breakfast;
	}
	
	@Column(name="ROOM_LEFT")
	public int[] getLeft() {
		return left;
	}
	public void setLeft(int[] left) {
		this.left = left;
	}

}
