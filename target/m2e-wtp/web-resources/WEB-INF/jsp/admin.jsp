<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
.even {
	background-color: silver;
}
</style>
<title>Admin Page</title>
</head>
<body>

<c:url value="/j_spring_security_logout" var="logoutUrl" />
 <center>
  <br /> <br /> <br />
  <h2>Admin | You are now logged in</h2>
  <p> <sec:authentication property="name"/>, welcome back to our Crown Hotel.</p>
  <br /> <br /> <br />
 <c2:if test="${fn:length(userList) >=0}">
	<table cellpadding="5">
	    <tr><td colspan="2" style="color: red">${message}</td></tr>
		<tr class="even">
		    <th>Username</th>
		    <th>Comment</th>
		    <th>Edit Comment</th>
			<th>Check History</th>
		</tr>
		<c2:forEach items="${userList}" var="user" varStatus="status">
			<tr class="<c2:if test="${status.count % 2 == 0}">even</c2:if>" >
			    <td>${user.userName}</td>
			    <td>${user.comment}</td>
			    <td><a href="<c:url value='/admin/comment/${user.id}.htm' />" >Edit</a></td>
			    <td><a href="<c:url value='/admin/history/${user.id}.htm' />" >Check</a></td>
			</tr>
		</c2:forEach>
	</table>
</c2:if>
  <h3><a href="${logoutUrl}">Logout</a></h3>
 </center>
</body>
</html>