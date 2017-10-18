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
<title>User Page</title>
</head>
<body>
<c:url value="/j_spring_security_logout" var="logoutUrl" />
 <center>
  <br /> <br /> <br />
  <h2>Crown Hotel</h2>
  <h2>Welcome back, <sec:authentication property="name"/></h2>
  <form:form action="user/queryroom" commandName="query">
	<table>
		<tr>
			<td>Client Name :</td>
			<td><form:input path="email" readonly="true"/></td>
		</tr>
		<tr>
			<td>Check in :</td>
			<td><form:input path="checkin" placeholder="yyyy-mm-dd"/></td>
		</tr>
		<tr>
			<td>Check out :</td>
			<td><form:input path="checkout" placeholder="yyyy-mm-dd"/></td>
		</tr>
		
		<tr>
			<td>Guest :</td>
			<td><form:select path="guest" placeholder = "0">
				<form:option value="0" label="Select" />
				<form:option value="1" label="one" />
				<form:option value="2" label="two" />
				<form:option value="3" label="three" />
				<form:option value="4" label="four" />
				<form:option value="5" label="five" />
				<form:option value="6" label="six" />
				<form:option value="7" label="seven" />
				<form:option value="8" label="eight" />
				<form:option value="9" label="nine" />
				<form:option value="10" label="ten" />
			</form:select></td>
		</tr>
		
		<tr>
			<td colspan="2"><input type="submit" value="Check Availability"></td>
		</tr>
		
	</table>
	<p>${errorinformation}</p>
</form:form>
<br /> <br /> <br />
<h2><sec:authentication property="name"/>, your history orders...</h2>
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
<h3><a href="${logoutUrl}">Logout</a></h3>
</center>
</body>
</html>