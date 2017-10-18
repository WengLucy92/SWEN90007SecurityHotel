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
<title>Update Comment</title>
</head>
<body>
 <center>
  <br /> <br /> <br />
  <h2>Crown Hotel</h2>
  <h2>Hi, <sec:authentication property="name"/></h2>
  <form:form action="updateComment" commandName="role">
	<table>
	    <tr>
			<td>Client ID : </td>
			<td><form:input path="id" readonly="true"/></td>
		</tr>
	    <tr>
			<td>Client Name :</td>
			<td><form:input path="userName" readonly="true"/></td>
		</tr>
		<tr>
			<td>New Comment :</td>
		</tr>
		<tr>
			<td><form:textarea path="comment" style="width:200px;height:80px;"/></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Update"></td>
		</tr>
		
	</table>
	<p>${errorinformation}</p>
</form:form>

</center>
</body>
</html>