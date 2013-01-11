<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1></h1>
	<a href = "DictionaryName.jsp">CreateDictionaryView.do</a></br>
	<a href = "Load.jsp">LoadDictionary.do</a></br>
	<a href = "logout.do">LogOut.do</a></br>
	<%
		if(request.getAttribute("successmsg")!= null)
		{
			out.println("<h1>You have Successfully Registered Now u can do the above operations!!</h1>");
		}
	
	
	%>


</body>
</html>