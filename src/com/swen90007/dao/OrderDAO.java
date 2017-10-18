package com.swen90007.dao;

import java.util.List;

import com.swen90007.model.Order;


public interface OrderDAO {
	
//	public void saveUser(Room user) ;
	public List<Order> listOrder() ;
	
	public void saveOrder(Order order);
	
	public Order findOrderById(Long id);
	
	public void update(Order order);
	
	public void changeBreakfast(Order order);
	
	public void cancel(Order order);
	
	public void insertDummyData();

}
