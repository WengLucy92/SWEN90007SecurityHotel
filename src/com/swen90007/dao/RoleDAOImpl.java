package com.swen90007.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.swen90007.model.*;

public class RoleDAOImpl implements RoleDAO {

	private HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public void saveRole(Role role) {
		hibernateTemplate.saveOrUpdate(role);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Role> listRole() {
		return hibernateTemplate.find("from Role");
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Role> listUserRole() {	
		return hibernateTemplate.find("from Role where role=?", "ROLE_USER");
	}
	
	public void insertDummyData(){
		
		Role luxin = new Role();
		Role qiulei = new Role();
		Role ruoyi = new Role();
		Role ziyue = new Role();
		
		luxin.setUserName("luxin");
		luxin.setRole("ROLE_ADMIN");
		qiulei.setUserName("qiulei");
		qiulei.setRole("ROLE_ADMIN");		
		
		ruoyi.setUserName("ruoyi");
		ruoyi.setRole("ROLE_USER");
		ruoyi.setLock(false);
		ruoyi.setComment("this is a real beautiful girl");
		ziyue.setUserName("ziyue");
		ziyue.setRole("ROLE_USER");
		ziyue.setComment("A real beautiful girl toooooo");
		ziyue.setLock(false);
		
		saveRole(luxin);
		saveRole(qiulei);
		saveRole(ruoyi);
		saveRole(ziyue);
	}
	
	@Override
	public void acquireLock(Role role){
		role.setLock(true);
		this.hibernateTemplate.update(role);
	};
	
	@Override
	public void releaseLock(Role role){
		role.setLock(false);
		this.hibernateTemplate.update(role);
	};
	
	@Override
	public void updateComment(Role role){
		this.hibernateTemplate.update(role);
	};
	
	@Override
	public Role findRoleById(Long id) {  
	    return this.hibernateTemplate.get(Role.class, id); 
	}  

}
