package com.swen90007.security;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.swen90007.dao.RoleDAO;
import com.swen90007.dao.RoomDAO;
import com.swen90007.dao.OrderDAO;
import com.swen90007.dao.UserDAO;
import com.swen90007.domain.DisplayUserOrder;
import com.swen90007.domain.SearchAvailableOrder;
import com.swen90007.model.Order;
import com.swen90007.model.Query;
import com.swen90007.model.Role;
import com.swen90007.model.Room;


@Controller
@RequestMapping("/")
public class LoginController {
	
	private UserDAO userDAO;
	private RoleDAO roleDAO;
	private OrderDAO orderDAO;
	private RoomDAO roomDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	
	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}
	
	@RequestMapping(value = "/initialize", method = RequestMethod.GET)
	public ModelAndView welcomePage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
	    userDAO.insertDummyData();
	    roleDAO.insertDummyData();
	    orderDAO.insertDummyData();
	    roomDAO.insertDummyData();
		return new ModelAndView("Welcome", modelMap);
	}
	
	 @RequestMapping(value = "/login", method = RequestMethod.GET)
	 public ModelAndView getLoginForm(@RequestParam(required = false) String authfailed, String logout, String denied) {
	  String message = "";
	  if (authfailed != null) {
	   message = "Invalid username of password, try again !";
	  } else if (logout != null) {
	   message = "Logged Out successfully, login again to continue !";
	  } else if (denied != null) {
	   message = "Access denied for this user !";
	  }
	  return new ModelAndView("login", "message", message);
	 }

	 @RequestMapping(value = "/user", method = RequestMethod.GET)
	 public ModelAndView getUserPage() {
		 UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
	     System.out.println(userDetails.getUsername());
		 ModelMap modelMap = new ModelMap();
		 modelMap.addAttribute("yourOrderList", DisplayUserOrder.userOrderList(orderDAO,userDetails.getUsername()));
		 Query newQuery = new Query();
		 newQuery.setEmail(userDetails.getUsername());
		 modelMap.addAttribute("query", newQuery);
	  return new ModelAndView("user", modelMap);
	 }

	 @RequestMapping(value = "/admin", method = RequestMethod.GET)
	 public ModelAndView geAdminPage() {
	     ModelMap modelMap = new ModelMap();
		 modelMap.addAttribute("userList", roleDAO.listUserRole());
		 return new ModelAndView("admin", modelMap);
	 }
	 
	 @RequestMapping(value = "/admin/comment/{id}.htm", method = RequestMethod.GET)
	 public ModelAndView chooseComment(@PathVariable("id") long id, HttpServletRequest request){
		 ModelMap modelMap = new ModelMap();
	     Role role = roleDAO.findRoleById(id);
	     if(role.getLock()){
	    	 modelMap.addAttribute("message", "Cannot edit the user's comment now. Please wait a moment.");
			 modelMap.addAttribute("userList", roleDAO.listUserRole());
			 return new ModelAndView("admin", modelMap);
	     }
	     roleDAO.acquireLock(role);
	     modelMap.addAttribute("role", role);
		 return new ModelAndView("comment", modelMap);
	 }
	 
	 @RequestMapping(value = "/admin/comment/updateComment", method = RequestMethod.POST)
	 public ModelAndView updateComment(Role role) {
	     ModelMap modelMap = new ModelMap();
	     System.out.println(role.getId());
	     System.out.println(role.getComment());
	     role.setLock(false);
	     role.setRole("ROLE_USER");
	     roleDAO.updateComment(role);
		 modelMap.addAttribute("userList", roleDAO.listUserRole());
		 return new ModelAndView("admin", modelMap);
	 }
	 
	 @RequestMapping(value = "/admin/history/{id}.htm", method = RequestMethod.GET)
	 public ModelAndView checkHistory(@PathVariable("id") long id, HttpServletRequest request){
		 ModelMap modelMap = new ModelMap();
		 String userName = roleDAO.findRoleById(id).getUserName();
		 modelMap.addAttribute("yourOrderList", DisplayUserOrder.userOrderList(orderDAO,userName));
		 modelMap.addAttribute("username", userName);
		 return new ModelAndView("orderHistory", modelMap);
	 }

	 @RequestMapping(value = "/403page", method = RequestMethod.GET)
	 public String ge403denied() {
	  return "redirect:login?denied";
	 }
	 
	 @RequestMapping(value = "/user/book/{id}", method = RequestMethod.GET)
	    public ModelAndView book(@PathVariable("id") long id, HttpServletRequest request){
	        Order bookingOrder = orderDAO.findOrderById(id);
//	        System.out.println(bookingOrder.getEmail());
	        orderDAO.update(bookingOrder);
	        Room room = roomDAO.findRoomById(bookingOrder.getRoomId());
	        roomDAO.update(room, bookingOrder.getRoomLeft());
	        Query newQuery = new Query();
	        newQuery.setCheckin(bookingOrder.getCheckin());
	        newQuery.setCheckout(bookingOrder.getCheckout());
	        newQuery.setEmail(bookingOrder.getEmail());
	        newQuery.setGuest(bookingOrder.getQueryGuest());
			ModelMap modelMap = new ModelMap();
			modelMap.addAttribute("information", "Thank you, your new order has been saved.");
			modelMap.addAttribute("yourOrderList", DisplayUserOrder.newUserOrderList(orderDAO, bookingOrder.getEmail()));
			modelMap.addAttribute("email", newQuery.getEmail());
			modelMap.addAttribute("checkin", newQuery.getCheckin());
			modelMap.addAttribute("checkout", newQuery.getCheckout());
			modelMap.addAttribute("guest", newQuery.getGuest());
			modelMap.addAttribute("availableOrderList", SearchAvailableOrder.listAvailableOrder(newQuery, roomDAO, orderDAO));
			return new ModelAndView("queryResult", modelMap);

	    }


}