<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<style type="text/css">
.even {
	background-color: silver;
}
</style>
<title>Order History</title>
</head>
<body>
 <center>
  <br /> <br /> <br />
  <h2>Here is ${username} order history</h2>
<c2:if test="${fn:length(yourOrderList) >=0}">
	<table cellpadding="5">
		<tr class="even">
		    <th>Check in</th>
		    <th>Check out</th>
			<th>Room Type</th>
			<th>Guest</th>
			<th>Price(AUD)</th>
			<th>Breakfast</th>
		</tr>
		<c2:forEach items="${yourOrderList}" var="yourOrder" varStatus="status">
			<tr class="<c2:if test="${status.count % 2 == 0}">even</c2:if>">
			    <td>${yourOrder.checkin}</td>
			    <td>${yourOrder.checkout}</td>
				<td>${yourOrder.roomType}</td>
				<td>${yourOrder.roomGuest}</td>
				<td>${yourOrder.roomPrice}</td>
				<td>${yourOrder.breakfast}</td>
	
			</tr>
		</c2:forEach>
	</table>
</c2:if>
</center>
</body>
</html>