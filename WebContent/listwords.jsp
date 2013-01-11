<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String msg = (String)request.getAttribute("msg");
	if(msg!="")
	{
			//out.println("<h4><p>"+"Part of sp=1:Noun"+" 2:Pronoun"+" 3:Verb"+" 4:Adverb"+" 5:Conjunction"+" 6:Preposition"+" 7:Disjunction"+"</p></h4>");			
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