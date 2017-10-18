package com.swen90007.dao;

import java.util.List;

import com.swen90007.model.Room;

public interface RoomDAO {
	
//	public void saveUser(Room user) ;
	public List<Room> listRoom() ;
	
	public void saveRoom(Room room);
	
	public void insertDummyData();
	
	public void update(Room room, int[] roomLeft);
	
	public Room findRoomById(Long id); 
}
