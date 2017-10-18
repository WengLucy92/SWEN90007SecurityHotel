package com.swen90007.model;


public class Query {
	
	private String email;
    private String checkin;
    private String checkout;
    private int guest;
    
    public void setEmail(String email){
    	this.email = email;
    }
	
    public String getEmail(){
    	return this.email;
    }
  
    public void setCheckin(String checkin){
    	this.checkin = checkin;
    }
	
    public String getCheckin(){
    	return this.checkin;
    }
    
    public void setCheckout(String checkout){
    	this.checkout = checkout;
    }
	
    public String getCheckout(){
    	return this.checkout;
    }
    

    public void setGuest(int guest){
    	this.guest = guest;
    }
    
    public int getGuest(){
    	return this.guest;
    }

}
