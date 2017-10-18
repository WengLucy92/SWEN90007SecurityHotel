package com.swen90007.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.swen90007.dao.OrderDAO;
import com.swen90007.dao.RoomDAO;
import com.swen90007.model.Order;
import com.swen90007.model.Query;
import com.swen90007.model.Room;


public class SearchAvailableOrder {
	
	public static List<Order> listAvailableOrder(Query query, RoomDAO roomDAO, OrderDAO orderDAO){
		List<Order> available = new ArrayList<Order>();
		List<Room> availableRoom = listAvailableRooms(query, roomDAO);
		
		for(Room room : availableRoom){
            Order newOrder = new Order();
            newOrder.setCheckin(query.getCheckin());
            newOrder.setCheckout(query.getCheckout());
            newOrder.setEmail(query.getEmail());
            newOrder.setRoomId(room.getId());
            newOrder.setRoomType(room.getType());
            newOrder.setRoomGuest(room.getGuest());
            newOrder.setRoomPrice(room.getPrice());
            newOrder.setRoomLeft(room.getLeft());
            newOrder.setOrderDate(generateQuerryDate(query));
            newOrder.setQueryGuest(query.getGuest());
            newOrder.setIsBooked(false);
            newOrder.setBreakfast("no breakfast");
            orderDAO.saveOrder(newOrder);
            available.add(newOrder);
		}
		
		return available;
	}
	
	public static List<Room> listAvailableRooms(Query query, RoomDAO roomDAO){
//		System.out.println("!!!!!!!!!!");
		List<Room> available = new ArrayList<Room>();
		List<Room> roomToRemove = new ArrayList<Room>();
				
		for(Room r: roomDAO.listRoom()){
			available.add(r);
		}
		
		int[] qurryDate =  generateQuerryDate(query);
		boolean hasRoom = true;
		
		for(Room room : available){
			hasRoom = true;
			for(int i=0; i<10; i++){
				if(room.getLeft()[i] < qurryDate[i]){
					hasRoom = false;
				}
			}
			
			if((!hasRoom) || (query.getGuest()<room.getGuest()-1)){
				roomToRemove.add(room);
//				System.out.println(room.getDescription());
			}
		}
		
		for (Room room : roomToRemove){
			available.remove(room);
		}
		
		
		
		return available;
	}
	
	public static int[] generateQuerryDate(Query query){

		Date today = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		int[] qurryDate = new int[10];
		try {
			java.util.Date checkinDate=sdf.parse(query.getCheckin());
			java.util.Date checkoutDate=sdf.parse(query.getCheckout());	
			
			for(int j=1; j<=10; j++){
				if((dayAfter(today, j).compareTo(checkinDate)>=0) && 
						(checkoutDate.compareTo(dayAfter(today, j))>=0)){
					qurryDate[j-1] = 1;
				}else{
					qurryDate[j-1] = 0;
				}
			}
		} catch (ParseException e) {
			
		} 
		
    return qurryDate;
	}
	
    public static Date dayAfter(Date today, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + n);
		return calendar.getTime();
		}
}
