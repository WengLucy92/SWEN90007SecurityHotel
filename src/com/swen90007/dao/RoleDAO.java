package com.swen90007.dao;

import java.util.List;


import com.swen90007.model.Role;

public interface RoleDAO {
	
	public void saveRole(Role role) ;
	public List<Role> listRole() ;
	public void insertDummyData();
	public List<Role> listUserRole();
	public void acquireLock(Role role);
	public void releaseLock(Role role);
	public void updateComment(Role role);
	public Role findRoleById(Long id);

}
