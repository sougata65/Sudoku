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
		response.sendRedirect("mygames");
	}
%>
<nav>
    <ul>
        <li><a href="play">Play</a></li>
        <li><a href="mygames" class="active">My Games</a></li>
        <li><a href="Leaderboards.jsp">Leaderboards</a></li>
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
<% 	ArrayList<ArrayList<String>> mygames = (ArrayList<ArrayList<String>>) request.getAttribute("mygames");
%>
<% int userlevel = (int) request.getSession().getAttribute("userlevel");
	if(userlevel==0)
	{
		userlevel=1;
	}
%>
<table id="usrlvl">
<tr> 
<td><h4 align="center"> Current Level : <%=userlevel %></h4></td>
<td><h4 align="center">Total Score : <%=request.getAttribute("totalScore") %></h4>
</tr>
</table>
<%
	if(mygames==null || mygames.get(0).isEmpty())
	{
		%>
		<h4 align = "center"><%="No games recorded so far." %></h4>
		<%
	}
	else
	{
		%>
<table border="2" align="center" id="data">
<tr>
<th>Level</th><th>Score</th><th>Date</th>
</tr>
		<%
		ArrayList<String> levels = mygames.get(0);
		ArrayList<String> scores = mygames.get(1);
		ArrayList<String> dates = mygames.get(2);
		for(int i=0;i<levels.size();i++)
		{
			String level = levels.get(i);
			String score = scores.get(i);
			String date = dates.get(i);
			%>
			<tr>
				<td><%= level%></td>
				<td><%= score %></td>
				<td><%= date %>
			</tr>
			<%
		}
	}
%>
</table>

</body>
</html>