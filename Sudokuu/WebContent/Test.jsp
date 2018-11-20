<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-2.1.0.min.js" ></script>
<script src="Login.js"></script>
<link rel="stylesheet" href="Test.css">
</head>
<body>
<script src="https://code.jquery.com/jquery-2.1.0.min.js" ></script>
<div id="formWrapper">

<div id="form">
<div class="logo">
			<p align="center"><img id="login" src="logo.png" style="width:100%;height:100%;"></p>
			<br><br>
			</div>
		<div class="form-item">
			<p class="formLabel">Email</p>
			<input type="text" name="username" id="username" class="form-style" autocomplete="off"/>
		</div>
		<div class="form-item">
			<p class="formLabel">Password</p>
			<input type="password" name="password" id="password" class="form-style" />
			<!-- <div class="pw-view"><i class="fa fa-eye"></i></div> -->	
		</div>
		<div class="form-item">
		<p class="pull-left"><a href="signup"><small>Register</small></a></p>
		<input type="submit" class="login pull-right" value="Log In">
		<div class="clear-fix"></div>
		</div>
</div>
</div>
</body>
</html>
	<table align="center">
	<tr><td>Username</td><td><input name="username" type="text" class="form-style" autocomplete="off"/></td></tr>
	<tr><td>Password</td><td><input name="password" type="password" class="form-style" autocomplete="off"/></td></tr>
	<tr><td>Full Name</td><td><input name="fullname" type="text" class="form-style" autocomplete="off"/></td></tr>
	<tr><td>Email</td><td><input name="email" type="text" class="form-style" autocomplete="off"/></td></tr>
	</table>
	<p align="center"><input type='submit' value='Sign Up'/></p>