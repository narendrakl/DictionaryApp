<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Dictionary Name</h1>
	<form action = "DictNameInt.jsp" method = "post">
		Enter the name of the Dictionary:<input type = "text" name = "dname">
		<input type = "submit"> 
	</form>
	<% 
			String msg=(String)request.getAttribute("errormsg");
			if (msg!=null)
			{
				out.println (msg);
			}
			
	%>



</body>
</html>