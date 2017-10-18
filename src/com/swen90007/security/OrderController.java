package com.swen90007.security;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.swen90007.dao.RoomDAO;
import com.swen90007.dao.OrderDAO;
import com.swen90007.domain.DisplayUserOrder;
import com.swen90007.model.Query;

import com.swen90007.domain.SearchAvailableOrder;
import com.swen90007.model.Order;

import com.swen90007.model.Room;

@Controller
@RequestMapping("/user/order")
public class OrderController {
	
	private RoomDAO roomDAO;
	private OrderDAO orderDAO;
	
//    private Query query;
	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}
	
	public void setOrderDAO(OrderDAO orderDAO){
		this.orderDAO = orderDAO;
	}

	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public ModelAndView book(@PathVariable("id") long id, HttpServletRequest request){
        Order bookingOrder = orderDAO.findOrderById(id);
//        System.out.println(bookingOrder.getEmail());
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
		modelMap.addAttribute("yourOrderList", DisplayUserOrder.userOrderList(orderDAO, bookingOrder.getEmail()));
		modelMap.addAttribute("email", newQuery.getEmail());
		modelMap.addAttribute("checkin", newQuery.getCheckin());
		modelMap.addAttribute("checkout", newQuery.getCheckout());
		modelMap.addAttribute("guest", newQuery.getGuest());
		modelMap.addAttribute("availableOrderList", SearchAvailableOrder.listAvailableOrder(newQuery, roomDAO, orderDAO));
		return new ModelAndView("queryResult", modelMap);

    }

	@RequestMapping(value = "/yesno/{id}.htm", method = RequestMethod.GET)
    public ModelAndView handleBreakfast(@PathVariable("id") long id, HttpServletRequest request){

        Order orderBreakfast = orderDAO.findOrderById(id);
        orderDAO.changeBreakfast(orderBreakfast);
//        System.out.println(bookingOrder.getEmail());
//        orderDAO.update(bookingOrder);
//        Room room = roomDAO.findRoomById(bookingOrder.getRoomId());
//        roomDAO.update(room, bookingOrder.getRoomLeft());
        Query newQuery = new Query();
        newQuery.setCheckin(orderBreakfast.getCheckin());
        newQuery.setCheckout(orderBreakfast.getCheckout());
        newQuery.setEmail(orderBreakfast.getEmail());
        newQuery.setGuest(orderBreakfast.getQueryGuest());
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("information", "Great! Your breakfast requirements has been changed.");
		modelMap.addAttribute("yourOrderList", DisplayUserOrder.userOrderList(orderDAO, orderBreakfast.getEmail()));
		modelMap.addAttribute("email", newQuery.getEmail());
		modelMap.addAttribute("checkin", newQuery.getCheckin());
		modelMap.addAttribute("checkout", newQuery.getCheckout());
		modelMap.addAttribute("guest", newQuery.getGuest());
		modelMap.addAttribute("availableOrderList", SearchAvailableOrder.listAvailableOrder(newQuery, roomDAO, orderDAO));
		return new ModelAndView("queryResult", modelMap);


    }
	
	@RequestMapping(value = "/cancel/{id}.htm", method = RequestMethod.GET)
    public ModelAndView cancelOrder(@PathVariable("id") long id, HttpServletRequest request){
		Order cancelOrder = orderDAO.findOrderById(id);
        orderDAO.cancel(cancelOrder);
        Room room = roomDAO.findRoomById(cancelOrder.getRoomId());
        roomDAO.update(room, cancelOrder.getRoomLeft());
        Query newQuery = new Query();
        newQuery.setCheckin(cancelOrder.getCheckin());
        newQuery.setCheckout(cancelOrder.getCheckout());
        newQuery.setEmail(cancelOrder.getEmail());
        newQuery.setGuest(cancelOrder.getQueryGuest());
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("information", "The selected order has been canceled.");
		modelMap.addAttribute("yourOrderList", DisplayUserOrder.userOrderList(orderDAO, cancelOrder.getEmail()));
		modelMap.addAttribute("email", newQuery.getEmail());
		modelMap.addAttribute("checkin", newQuery.getCheckin());
		modelMap.addAttribute("checkout", newQuery.getCheckout());
		modelMap.addAttribute("guest", newQuery.getGuest());
		modelMap.addAttribute("availableOrderList", SearchAvailableOrder.listAvailableOrder(newQuery, roomDAO, orderDAO));
		return new ModelAndView("queryResult", modelMap);

    }
	
	@RequestMapping(value = "/confirm.htm", method = RequestMethod.GET)
	public void confirm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.getRequestDispatcher("/WEB-INF/jsp/thankyou.jsp").forward(request,response);
	}
	
}
