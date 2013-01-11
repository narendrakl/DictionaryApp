<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dictionary MVC</title>
</head>
<body>

	<form action="SearchInt.jsp" method="post">
		Enter the word to Search:<input type="text" name="word"/>
		<input type="submit"/>
	</form>
	<%
		String msg = (String)request.getAttribute("msg");
	if(msg!=null)
	{
		if(msg.equals("Word is empty"))
		{
			out.println("<h3>"+"Sir Yake Field na Empty Bidtira!! I cant search"+"</h3>");
		}
		else if(msg.contains("Word"))
		{
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
			
		else
			out.println("<h3>"+"Nimma Dictionary nalli Ee Word illa!!"+"</h3>");
	}
	else
		out.println(" ");
	%>


</body>
</html>