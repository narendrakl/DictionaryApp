<%@page import="com.naren.register.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="DeleteDetailsInt.jsp" method="post">
		Enter the word to Delete:<input type="text" name="word"/>
		Select the Part of Speech:
		<select name="pos">
			<option>Noun</option>
			<option>Pronoun</option>
			<option>Verb</option>
			<option>Adverb</option>
			<option>Conjunction</option>
			<option>Preposition</option>
			<option>Disjunction</option>
		</select></br>
		<input type="submit"/>
	
	</form>
	<%
		String msg=(String)request.getAttribute("msg1");
		if(msg!=null)
		{
				if(msg.equals("field is empty"))
				out.println("<h1>"+"Sir Empty field Bidabedi!!"+"</h1>");
			
			else if(msg.equals("no word"))
				out.println("<h1>"+"Mostly Ee word nimma dictionary nalli illa!!");
		}
		else
		{
			out.println("");
		}
	
	%>

</body>
</html>