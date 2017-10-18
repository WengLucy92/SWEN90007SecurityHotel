package com.swen90007.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.swen90007.dao.OrderDAO;
import com.swen90007.dao.RoomDAO;
import com.swen90007.domain.CheckDate;
import com.swen90007.domain.DisplayUserOrder;
import com.swen90007.domain.SearchAvailableOrder;
import com.swen90007.model.Order;
import com.swen90007.model.Query;
import com.swen90007.model.Room;

@Controller
public class RoomController extends MultiActionController {

	private RoomDAO roomDAO;
	private OrderDAO orderDAO;
	
//    private Query query;
	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}
	
	public void setOrderDAO(OrderDAO orderDAO){
		this.orderDAO = orderDAO;
	}

//	public Query getQueryObject(){
//		return new Query();
//	}
	
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("roomList", roomDAO.listRoom());
//		modelMap.addAttribute("room", new Room());
		return new ModelAndView("roomForm", modelMap);
	}
	
	public ModelAndView insertdummy(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		roomDAO.insertDummyData();
		return new ModelAndView("redirect:list.htm");
	}
	
	 @RequestMapping(value = "/queryroom", method = RequestMethod.POST)
	public ModelAndView queryroom(HttpServletRequest request,
			HttpServletResponse response, Query query) throws Exception {

		if (CheckDate.checkDate(query.getCheckin(), query.getCheckout())&&(query.getGuest()>0)){
//			return new ModelAndView("redirect:queryPage.htm");
			ModelMap modelMap = new ModelMap();
//			modelMap.addAttribute("query", new Query());
			modelMap.addAttribute("email", query.getEmail());
			modelMap.addAttribute("checkin", query.getCheckin());
			modelMap.addAttribute("checkout", query.getCheckout());
			modelMap.addAttribute("guest", query.getGuest());
			modelMap.addAttribute("availableOrderList", SearchAvailableOrder.listAvailableOrder(query, roomDAO, orderDAO));
			return new ModelAndView("queryResult", modelMap);
		}else{
			return new ModelAndView("redirect:queryErrorPage.htm");
		}
//		ModelMap modelMap = new ModelMap();
//		modelMap.addAttribute("query", new Query());
	}
	
	
	public ModelAndView queryErrorPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("query", new Query());
		modelMap.addAttribute("errorinformation", "Sorry, invalid check in or check out date");
		modelMap.addAttribute("roomList", roomDAO.listRoom());
		modelMap.addAttribute("room", new Room());
		return new ModelAndView("welcomePage", modelMap);
	}
	
	public ModelAndView welcomePage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("query", new Query());
		roomDAO.insertDummyData();
		modelMap.addAttribute("roomList", roomDAO.listRoom());
		modelMap.addAttribute("room", new Room());
		return new ModelAndView("welcomePage", modelMap);
	}
	
//	public void jumpToNewPage(HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		request.getRequestDispatcher("/WEB-INF/jsp/welcomePage.jsp").forward(request,response);
//		//return new ModelAndView("welcomePage");
//	}
	
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("errorinformation", "Sorry, invalid check in or check out date");
        return new ModelAndView("queryResult", modelMap);
	}
	
//	@RequestMapping(value = "/room/delete/{id}.htm", method = RequestMethod.GET)
//    public ModelAndView delete(@PathVariable("id") long id, HttpServletRequest request){
//    	System.out.println("!!!!!!!!!!!!!!!!!!!");
////        long roomId = Long.parseLong(request.getParameter("id"));
//		ModelMap modelMap = new ModelMap();
//		modelMap.addAttribute("errorinformation", "Sorry, invalid check in or check out date");
//        return new ModelAndView("queryResult", modelMap);
//
//    }
	
	@RequestMapping(value = "/room/delete/{id}.htm", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") long id, HttpServletRequest request){
    	System.out.println("!!!!!!!!!!!!!!!!!!!");
//        long roomId = Long.parseLong(request.getParameter("id"));
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("errorinformation", "Sorry, invalid check in or check out date");
        return new ModelAndView("queryResult", modelMap);

    }

}
