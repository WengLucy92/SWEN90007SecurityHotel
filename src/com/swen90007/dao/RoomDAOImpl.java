package com.swen90007.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.swen90007.model.*;

public class RoomDAOImpl implements RoomDAO {

	private HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Room> listRoom() {
		return hibernateTemplate.find("from Room");		
	}
	
	public void saveRoom(Room room) {  
	    this.hibernateTemplate.saveOrUpdate(room);  
	}  
	
	public void insertDummyData(){
		
		String[] services = new String[]{"24 hours room service","newspaper"};
		int[] left = new int[]{2,2,2,2,2,2,2,2,2,2};
		
		Room luxeKingRoom = new Room();
		luxeKingRoom.setBreakfast(true);
		luxeKingRoom.setDescription("1 king bed");
		luxeKingRoom.setGuest(2);
		luxeKingRoom.setType("Luxe King Room");
		luxeKingRoom.setPrice(774);
		luxeKingRoom.setServices(services);
		luxeKingRoom.setLeft(left);
		saveRoom(luxeKingRoom);
		
		Room luxeTwinRoom = new Room();
		luxeTwinRoom.setBreakfast(true);
		luxeTwinRoom.setDescription("2 queen beds");
		luxeTwinRoom.setGuest(4);
		luxeTwinRoom.setType("Luxe Twin Room");
		luxeTwinRoom.setPrice(774);
		luxeTwinRoom.setServices(services);
		luxeTwinRoom.setLeft(left);
		saveRoom(luxeTwinRoom);
	}
	
	public void update(Room room, int[] roomLeft){
		room.setLeft(roomLeft);
		this.hibernateTemplate.update(room);
	}
	
	public Room findRoomById(Long id) {  
	    return this.hibernateTemplate.get(Room.class, id); 
	}  


}
