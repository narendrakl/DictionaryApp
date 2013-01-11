<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dictionary MVC</title>
</head>
<body>
	<a href="WordDetails.jsp">Add Word</a></br>
	<a href="SearchDetails.jsp">Search word</a></br>
	<a href="EditDetails.jsp">Edit word</a></br>
	<a href="DeleteDetails.jsp">Delete Word</a></br>
	<a href="listwords.do">List word</a></br>
	<%
		
		String msg = (String)request.getAttribute("msg");
	if(msg!=null)
	{
		if(msg.equals("deleted"))
		{
			out.println("<h1>"+"Oho Yakri delete maadbitri..No Problem!!"+"</h1>");
		}
		
		{
			out.println("Success");
		}
	}
	

	%>
	
</body>
</html>