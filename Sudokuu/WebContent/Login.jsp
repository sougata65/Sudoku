<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-2.1.0.min.js" ></script>
<script src="Login.js"></script>


<link rel="stylesheet" href="Login.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

<form action="login" method="post" align="center">
<div id="formWrapper">

<div id="form">
<div class="logo">
			<p align="center"><img id="login" src="logo.png" style="width:100%;height:100%;"></p>
			<br><br>
			</div>
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
		<div class="form-item">
			<p class="formLabel">Username</p>
			<input type="text" name="username" id="username" class="form-style" autocomplete="off"/>
		</div>
		<div class="form-item">
			<p class="formLabel">Password</p>
			<input type="password" name="password" id="password" class="form-style" />
			<!-- <div class="pw-view"><i class="fa fa-eye"></i></div> -->	
		</div>
		<div class="form-item">
		<p class="pull-left"><a href="signup"><small>Sign Up</small></a></p>
		<input type="submit" class="login pull-right" value="Log In">
		<div class="clear-fix"></div>
		</div>
</div>
</div>

</form>


</body>
</html>