<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dictionary MVC</title>
</head>
<body>
	<h1>Registration Page</h1>
	<form action="RegInt.jsp" method="post">
	
		Enter Your Email Correctly:<input type="text" name="email" value = <%=request.getParameter("email") == null?"" :request.getParameter("email")%>></br>
		
		Enter Your Password:<Input type="password" name="pwd"/></br>	
	
		Repeat Your Password:<input type="password" name="rpwd"/></br>
		
		<input type="submit"/>
	</form>
	<%
		if(request.getAttribute("errormsg")!=null)
		{
			out.println("<h1>Erroru Swaami Erroru!!!</h1>");
			out.println(request.getAttribute("errormsg"));
						
		}
	
	%>



</body>
</html>