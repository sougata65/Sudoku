<%@page import="com.sudokuu.constants.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="CreateGame.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<nav>
    <ul>
        <li><a href="play">Play</a></li>
        <li><a href="mygames">My Games</a></li>
        <li><a href="Leaderboards.jsp">Leaderboards</a></li>
        <li><a href="CreateGame.jsp" class="active">Create Game</a></li>
        <li><a href="logout">Logout</a></li>    
    </ul>
</nav>

<% 	String usertype = (String) request.getSession().getAttribute("usertype");
	String username = (String) request.getSession().getAttribute("username");

	String access = (String) request.getAttribute("access");
	if(access==null)
	{
		response.sendRedirect("creategame");
	}
		
	else if(access.equals("no"))
	{
		
		%>
			<article>
			<div>
				
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
				
				
				<form action="creategame" method="post" align="center">
					<br>
					<br><table align="center"> Enter game template
				<% 	int[][] template = (int[][]) request.getAttribute("template");
					int i,j;
					int[][] matrix = new int[9][9];
					for(i=0;i<9;i++)
					{
						%><tr><%
						for(j=0;j<9;j++)
						{
							if(template!=null)
							{
								if(template[i][j]==0)
								{
								%>	<td><input type="text" pattern = "[1-9]{1}" name="<%= (""+i+""+j) %>" value="" size="1" class = "notfixed"></td> <%
								}
								else{
								%>	<td><input type="text" pattern = "[1-9]{1}" name="<%= (""+i+""+j) %>" value="<%=template[i][j] %>" size="1" class = "notfixed"></td> <%
								}
							}
							else
							{
								%>	<td><input type="text" pattern = "[1-9]{1}" name="<%= (""+i+""+j) %>" value="" size="1" class = "notfixed"></td> <%
							}
						}
						%> </tr> <%
					}
					
				%> </table>
				
				<button type="submit" name="savegame" align="center" id="savegame">Save Game</button>
				</form>
			
			
			</div>
			<h1 style="color:#F54D2B;">This page is reserved for admins only</h1>
			</article>
		<%	
	}
	else{
		%>


<form action="creategame" method="post" align="center">
	<table align="center"> <h3>Enter game template</h3>
	<h3>Level : <input type="number" min="1" name="level" align="center"  value = "<%=request.getAttribute("nextLevel") %>" id="leveldisplay" readonly/></h3>
	<%
String msg = (String) request.getAttribute("msg");
if(msg!=null)
{
	%> <h4 align="center"><%=msg %></h4> <%
}
String redmsg = (String) request.getAttribute("redmsg");
if(redmsg!=null)
{
	%> <h4 align="center" class="redmsg"><%=redmsg %></h4> <%
}
String greenmsg = (String) request.getAttribute("greenmsg");
if(greenmsg!=null)
{
	%> <h4 align="center" class="greenmsg"><%=greenmsg %></h4> <%
}
%>
	
<% 	int[][] template = (int[][]) request.getAttribute("template");
	int i,j;
	int[][] matrix = new int[9][9];
	for(i=0;i<9;i++)
	{
		%><tr><%
		for(j=0;j<9;j++)
		{
			if(template!=null)
			{
				if(template[i][j]==0)
				{
				%>	<td><input type="text" pattern = "[1-9]{1}" name="<%= (""+i+""+j) %>" value="" size="1" class = "notfixed"></td> <%
				}
				else{
				%>	<td><input type="text" pattern = "[1-9]{1}" name="<%= (""+i+""+j) %>" value="<%=template[i][j] %>" size="1" class = "notfixed"></td> <%
				}
			}
			else
			{
				%>	<td><input type="text" pattern = "[1-9]{1}" name="<%= (""+i+""+j) %>" value="" size="1" class = "notfixed"></td> <%
			}
		}
		%> </tr> <%
	}
%> </table>

<button type="submit" name="savegame" align="center" id="savegame">Save Game</button>
</form>
<% } %>
</body>
</html>