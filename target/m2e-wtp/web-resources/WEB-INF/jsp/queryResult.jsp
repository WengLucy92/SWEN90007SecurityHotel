<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
.even {
	background-color: silver;
}
</style>
<title>Welcome to Crown Hotel</title>
</head>
<body>
Your Query Message:<br>
Check In: ${checkin}<br>
Check Out: ${checkout}<br>
Guests: ${guest}
<p>${information}</p>

<h3>Your Order</h3>
<c:if test="${fn:length(roomList) >=0}">
	<table cellpadding="5">
		<tr class="even">
			<th>Room Type</th>
			<th>Guest</th>
			<th>Price(AUD)</th>
			<th>Do you want breakfast?</th>
			<th>Cancel</th>
					
		</tr>
		<c:forEach items="${yourOrderList}" var="yourOrder" varStatus="status">
			<tr class="<c:if test="${status.count % 2 == 0}">even</c:if>">
				<td>${yourOrder.roomType}</td>
				<td>${yourOrder.roomGuest}</td>
				<td>${yourOrder.roomPrice}</td>
				<td>${yourOrder.breakfast}
				<a href="<c:url value='/order/yesno/${yourOrder.id}.htm' />" >Yes/No</a></td>
				<td><a href="<c:url value='/order/cancel/${yourOrder.id}.htm' />" >Cancel</a></td>
	
			</tr>
		</c:forEach>
	</table>
</c:if>



<h3>There are some rooms could meet your requirements:</h3>
<c:if test="${fn:length(roomList) >=0}">
	<table cellpadding="5">
		<tr class="even">
			<th>Room Type</th>
			<th>Guest</th>
			<th>Price(AUD)</th>
			<th>Booking Now</th>				
		</tr>
		<c:forEach items="${availableOrderList}" var="order" varStatus="status">
			<tr class="<c:if test="${status.count % 2 == 0}">even</c:if>">
				<td>${order.roomType}</td>
				<td>${order.roomGuest}</td>
				<td>${order.roomPrice}</td>
				<td><a href="<c:url value='/user/book/${order.id}.htm' />" >Booking</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>

</body>
</html>