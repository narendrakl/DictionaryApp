<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dictionary MVC</title>
</head>
<body>
	<form action="EditDetailsInt.jsp" method="post">
		Enter the word to Edit:<input type="text" name="word"/>
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
		String msg = (String)request.getAttribute("msg");
		if(msg!=null)
		{
			out.println(msg);
		}
	%>


</body>
</html>