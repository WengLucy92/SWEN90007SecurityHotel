package com.swen90007.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.swen90007.model.*;

public class UserDAOImpl implements UserDAO {

	private HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public void saveUser(User user) {
		hibernateTemplate.saveOrUpdate(user);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<User> listUser() {
		return hibernateTemplate.find("from User");
	}
	
	public void insertDummyData(){
		
		User admin = new User();
		User client = new User();
		
		admin.setUserName("Admin");
		admin.setPassword("1234");	
		admin.setEnabled(true);
		
		client.setUserName("Client");
		client.setPassword("12345");
		client.setEnabled(true);
		

		
		saveUser(admin);
		saveUser(client);
		
	}

}
