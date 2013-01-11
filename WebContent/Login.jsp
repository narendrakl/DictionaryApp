<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dictionary MVC</title>
</head>
<body>
	<h1> Log In Page</h1>
	<form action="LogInt.jsp" method = "post">
		Enter Your Email:<input type = "text" name = "email"/></br>
		Enter Password:<input type = "password" name = "pwd"/></br>
		<input type ="submit"/>
	</form>
	<%
		if(request.getAttribute("errormsg")!= null)
		{
			out.println("<h1>Ello Eno Aithu Kelgade Iruva Msg Oodi!!</h1>");
			out.println(request.getAttribute("errormsg"));
		}

	%>

</body>
</html>