<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<h1>Enter the Dict Name to Load</h1>
	<form action = "LoadInt.jsp" method = "post">
		Enter the name of the Dictionary:<input type = "text" name="dname"/>
		<input type = "submit"> 
		
	</form>
	<%
		String msg = (String)request.getAttribute("msg");
		if(msg!=null)
		{
			out.println("<h4>"+"U are seeing this list means that name dict not exist"+"</h4>");
			if(msg.contains("@"))
			{
				int i=0;
				String a[]=msg.split("@");
				while(!a[i].contains("0"))
				{
					out.println("<h4><p>"+a[i]+"</p></h4>");
					i++;
				}
				
			}
			
		}
		
	%>

</body>
</html>