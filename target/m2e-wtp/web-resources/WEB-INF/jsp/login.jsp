<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Login Page</title>
</head>
<body>

<form method="post" action="j_spring_security_check" >
	<table>
	    <tr><td colspan="2" style="color: red">${message}</td></tr>
        <tr><td>User:</td><td><input type='text' name='j_username'></td></tr>
        <tr><td>Password:</td><td><input type='password' name='j_password'/></td></tr>
		
		<tr>
			<td colspan="2"><input type="submit" value="Login"></td>
		</tr>
	</table>
</form>

</body>
</html>