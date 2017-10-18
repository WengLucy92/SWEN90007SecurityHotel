package com.swen90007.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CheckDate {
	
    public static boolean checkDate(String checkin, String checkout){
    	
    	if (isValidDate(checkin)&&isValidDate(checkout)){
    		if (canBookDate(checkin, checkout)){
    			return true;
    		}
    	}
    	return false;
    }
	
	public static boolean isValidDate(String str) {
		boolean convertSuccess=true;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		   try{
		      format.setLenient(false);
		      format.parse(str);
		   } catch (ParseException e) {
//		       e.printStackTrace();
		       convertSuccess=false;
//		       System.out.println(str);		       
		   } 
		   
		   return convertSuccess;
		}
	
	public static boolean canBookDate(String checkin, String checkout){
		boolean canBook = true;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date();
		Date lastBookDate = tenDayAfter(currentDate);
		try {
			java.util.Date checkinDate=sdf.parse(checkin);
			java.util.Date checkoutDate=sdf.parse(checkout);
	
			if (checkinDate.compareTo(checkoutDate) >= 0){
				canBook = false;
			}
			
			if(currentDate.compareTo(checkinDate) >0){
				canBook = false;
			}
			
			if(checkoutDate.compareTo(lastBookDate) >0){
				canBook = false;
			}			
			
		} catch (ParseException e) {
			
		} 
		return canBook;
	}
	
	 public static Date tenDayAfter(Date today) {
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(today);
		 calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 10);
		 return calendar.getTime();
		 }
}
