package com.swen90007.domain;

import java.util.ArrayList;
import java.util.List;

import com.swen90007.dao.OrderDAO;
import com.swen90007.model.Order;

public class DisplayUserOrder {
    
	public static List<Order> userOrderList(OrderDAO orderDAO, String email){
//		System.out.println("!!!!!!!!!!");
		List<Order> userOrders = new ArrayList<Order>();
		List<Order> notUserOrders = new ArrayList<Order>();
				
		for(Order o: orderDAO.listOrder()){
			userOrders.add(o);
			System.out.println("ADD");
		}
		
		for(Order o: userOrders){
			
			if((!o.getEmail().equals(email)) || (!o.getIsBooked())){
				notUserOrders.add(o);
				System.out.println("Delete");
				System.out.println(o.getEmail() != email);
				System.out.println(!o.getIsBooked());
			}
		}
		
		for(Order o: notUserOrders){
			userOrders.remove(o);
			System.out.println("REAL Delete");
		}
		
		return userOrders;
	}
	
	public static List<Order> newUserOrderList(OrderDAO orderDAO, String email){
//		System.out.println("!!!!!!!!!!");
		List<Order> userOrders = new ArrayList<Order>();
		List<Order> notUserOrders = new ArrayList<Order>();
				
		for(Order o: orderDAO.listOrder()){
			userOrders.add(o);
			System.out.println("ADD");
		}
		
		for(Order o: userOrders){
			
			if((!o.getEmail().equals(email)) || (!o.getIsBooked()) ||(o.getCheckin().equals("2017-02-14"))){
				notUserOrders.add(o);
				System.out.println("Delete");
				System.out.println(o.getEmail() != email);
				System.out.println(!o.getIsBooked());
			}
		}
		
		for(Order o: notUserOrders){
			userOrders.remove(o);
			System.out.println("REAL Delete");
		}
		
		return userOrders;
	}
}
