<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dictionary MVC</title>
</head>
<body>
	<form action="WordDetailsInt.jsp" method = "post">
		Enter the Word:<input type="text" name="word"/></br>
		Enter the Meaning</br>of the above word:<input type="text" name="meaning"/></br>
		Enter the Example:<input type="text" name="example"/></br>
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
		String msg1 =(String)request.getAttribute("errmsg");
			if(msg1!=null)
			{
				out.println("<h1>"+"Ayyo Karmave!!!"+"</h1>");
				out.println("<h3>"+msg1+"</h3>");
			}
			else
			{
				String msg = (String)request.getAttribute("msg");
				if(msg!=null)
				out.println("<h3>"+msg+"</h3>");
				
				else
					out.println(" ");
			}
				
	
	%>


</body>
</html>