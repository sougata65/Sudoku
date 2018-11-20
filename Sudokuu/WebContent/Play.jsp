<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.sudokuu.service.*"
	import="com.sudokuu.dao.*" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="Play.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Play</title>
</head>
<body>
<% 	String access=(String) request.getAttribute("access");
	if(access==null)
	{
		response.sendRedirect("play");
	}
%>
<nav>
    <ul>
        <li><a href="play" class="active" >Play</a></li>
        <li><a href="mygames">My Games</a></li>
        <li><a href="leaderboards">Leaderboards</a></li>
        <li><a href="creategame">Create Game</a></li>
        <li><a href="logout">Logout</a></li>    
    </ul>
</nav>
<h3 align="center" >Level : <%=request.getSession().getAttribute("userlevel") %></h3>
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

	<%
		int userlevel =(int) request.getSession().getAttribute("userlevel");
		int maxLevelAdded = new PlayService().getMaxLevelAddedTillNow();
		if(userlevel>maxLevelAdded)
		{
			%>
			<h3 align="center" class="redmsg">No further levels added yet. Please check back soon.</h3>
			<%
		}
		else{
			ArrayList<int[][]> game = (ArrayList<int[][]>) request.getAttribute("game");
			int[][] matrixtemplate = game.get(0);
			int[][] matrixsavedgame = game.get(1);
			if(game.get(1)==null)
			{
				matrixsavedgame=new int[9][9];
			}
			int i, j;
	%>
	<form align="center" action="play" method="post">
		<table align="center" id="csstable" cellspacing="0">
			<%
				
			%>
				<%
					for (i = 0; i < 9; i++) {
				%>
			
			<tr>
				<%
					for (j = 0; j < 9; j++) {
							String cellname = "" + i + j;
							int val1 = matrixtemplate[i][j];
							if (val1 == 0) {
								int val2 = matrixsavedgame[i][j];
								if (val2 == 0) {
				%>
				<td><input type="text" name="<%=cellname%>" pattern="[0-9]{1}"
					title="number ranging from 1 to 9" size="1" value="" class="notfixed"></td>
				<%
					} else {
				%>
				<td><input type="text" name="<%=cellname%>" pattern="[1-9]{1}"
					title="number ranging from 1 to 9" size="1" value="<%=val2%>" class="notfixed"></td>
				<%
					}
							} else {
				%>
				<td><input type="text"
					name="<%=cellname%>" pattern="[1-9]{1}"
					title="number ranging from 1 to 9" size="1"
					value="<%=matrixtemplate[i][j]%>" class="fixed" readonly></td>
				<%
					}
						}
				%>
			</tr>
			<%
				}
			%>
		</table>
		<button name="action" type="submit" value="submit" class="button" id="submit">Submit</button>
		<button name="action" type="submit" value="save" class="button" id="save">Save</button>
		<button name="action" type="submit" value="solve" class="button" id="solve">Solve</button>
	</form>
	<% } %>
</body>
</html>