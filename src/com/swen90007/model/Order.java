package com.swen90007.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import sun.nio.cs.ext.ISCII91;

@Entity
@Table(name="Order_NEW")
public class Order {

	private Long id;
	private Long roomId;
	private String email;
	private String checkin;
	private String checkout;
	private String roomType;
	private int roomGuest;
	private int roomPrice;
	private int[] roomLeft;
	private int[] orderDate;
	private boolean isBooked;
	private int queryGuest;
	private String breakfast;
	
	@Id
	@GeneratedValue
	@Column(name="ORDER_ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="ORDER_ROOMID")
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	
	@Column(name="ORDER_EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="ORDER_CHECKIN")
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	
	@Column(name="ORDER_CHECKOUT")
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	
	@Column(name="ORDER_ROOMTYPE")
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	@Column(name="ORDER_ROOMGUEST")
	public int getRoomGuest() {
		return roomGuest;
	}
	public void setRoomGuest(int roomGuest) {
		this.roomGuest = roomGuest;
	}
	
	@Column(name="ORDER_ROOMPRICE")
	public int getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}
	
	@Column(name="ORDER_ROOMLEFT")
	public int[] getRoomLeft() {
		return roomLeft;
	}
	public void setRoomLeft(int[] roomLeft) {
		this.roomLeft = roomLeft;
	}
	
	@Column(name="ORDER_ORDERDATE")
	public int[] getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(int[] orderDate) {
		this.orderDate = orderDate;
	}
	
	@Column(name="ORDER_ISBOOKED")
	public boolean getIsBooked() {
		return isBooked;
	}
	public void setIsBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	
	@Column(name="ORDER_QUERYGUEST")
	public int getQueryGuest() {
		return queryGuest;
	}
	public void setQueryGuest(int queryGuest) {
		this.queryGuest = queryGuest;
	}
	
	@Column(name="ORDER_BREAKFAST")
	public String getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}

}
