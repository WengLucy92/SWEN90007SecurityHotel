package com.swen90007.dao;

import java.util.List;
import com.swen90007.model.User;

public interface UserDAO {
	
	public void saveUser(User user) ;
	public List<User> listUser() ;
	public void insertDummyData();
}
