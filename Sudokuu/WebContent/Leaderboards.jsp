<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="Scores.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% 	String access = (String) request.getAttribute("access");
	if(access==null)
	{
		response.sendRedirect("leaderboards");
	}
%>
<nav>
    <ul>
        <li><a href="play" >Play</a></li>
        <li><a href="mygames">My Games</a></li>
        <li><a href="Leaderboards.jsp" class="active">Leaderboards</a></li>
        <li><a href="CreateGame.jsp">Create Game</a></li>
        <li><a href="logout">Logout</a></li>    
    </ul>
</nav>

<%
String msg = (String) request.getAttribute("msg");
if(msg!=null)
{
	%> <h3 align="center"><%=msg %></h3> <%
}
String redmsg = (String) request.getAttribute("redmsg");
if(redmsg!=null)
{
	%> <h3 align="center" class="redmsg"><%=redmsg %></h3> <%
}
String greenmsg = (String) request.getAttribute("greenmsg");
if(greenmsg!=null)
{
	%> <h3 align="center" class="greenmsg"><%=greenmsg %></h3> <%
}
%>

<% 
LinkedHashMap<String,Integer> leaderboards = (LinkedHashMap<String,Integer>) request.getAttribute("leaderboards");
%>
<table border="2" align="center">
<tr>
<th>Rank</th><th>Username</th><th>Total Score</th>
</tr>
<%
	if(leaderboards==null)
	{
		%>
		<%="No games recorded so far." %>
		<%
	}
	else
	{
		int serial = 1;
		for(Map.Entry<String,Integer> entry : leaderboards.entrySet())
		{
			String username =(String) entry.getKey();
			int score = (int) entry.getValue();
			String sessUsername = (String) request.getSession().getAttribute("username");
			if(username.equals(sessUsername))
			{
				%>
				<tr>
				<td class="selected"><%= serial %></td>
				<td class="selected"><%= username%></td>
				<td class="selected"><%= score %></td>
			</tr>
			<%
			}
			else{
			%>
			<tr>
				<td><%= serial %></td>
				<td><%= username%></td>
				<td><%= score %></td>
			</tr>
			<%
			}
			serial++;
		}
	}
%>
</table>
</body>
</html>