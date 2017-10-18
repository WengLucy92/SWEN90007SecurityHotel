package com.swen90007.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.swen90007.model.Order;

public class OrderDAOImpl implements OrderDAO{

	private HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Order> listOrder() {
		return hibernateTemplate.find("from Order");		
	}
	
	public void saveOrder(Order order) {  
	    this.hibernateTemplate.saveOrUpdate(order);  
	}  
	
	public Order findOrderById(Long id) {  
	    return this.hibernateTemplate.get(Order.class, id); 
	}  
	
	public void update(Order order){
		order.setIsBooked(true);
		int[] newRoomLeft = new int[10];
		int[] roomleft = order.getRoomLeft();
		int[] orderDate = order.getOrderDate();
		for(int i=0; i<10; i++){
			newRoomLeft[i] = roomleft[i]-orderDate[i];
		}
		order.setRoomLeft(newRoomLeft);
		
		this.hibernateTemplate.update(order);
	}
	
	public void changeBreakfast(Order order){
		String noBreakfast = "no breakfast";
		if (order.getBreakfast().equals(noBreakfast)){
			order.setBreakfast("breakfast included");
		}else{
			order.setBreakfast("no breakfast");
		}
		
		this.hibernateTemplate.update(order);
	}
	
	public void cancel(Order order){
		order.setIsBooked(false);
		int[] newRoomLeft = new int[10];
		int[] roomleft = order.getRoomLeft();
		int[] orderDate = order.getOrderDate();
		for(int i=0; i<10; i++){
			newRoomLeft[i] = roomleft[i]+orderDate[i];
		}
		order.setRoomLeft(newRoomLeft);
		
		this.hibernateTemplate.update(order);
	}
	
	public void insertDummyData() {  
	     Order order1 = new Order();
	     order1.setEmail("ruoyi");
	     order1.setCheckin("2017-02-14");
	     order1.setCheckout("2017-02-15");
	     order1.setRoomType("King Room");
	     order1.setRoomGuest(2);
	     order1.setRoomPrice(800);
	     order1.setBreakfast("breakfast included");
	     order1.setIsBooked(true);
	     saveOrder(order1);
	     
	} 

}
