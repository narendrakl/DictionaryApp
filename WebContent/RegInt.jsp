<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dictionary MVC</title>
</head>
<body>

	<jsp:useBean id="reg" class="com.naren.register.RegBean" scope="request">
	
	<jsp:setProperty name="reg" property="*"/>
	
	</jsp:useBean>
	<jsp:forward page="register.do"/>

</body>
</html>