<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Secret</title>
<link  href="/css/reset.css" rel="stylesheet" type="text/css" />
<link  href="/css/decorate.css" rel="stylesheet" type="text/css" />
<link  href="/css/formStyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
</head>
<body>
<div id="wrap" class="boxin-shadow spotlight">

<div class="boxout-shadow all-radius form-style">
	<form action="/Login" method="post">
	<h3>LoginForm</h3>
	<ul>
		<li><div class="sprite-id ico"></div><span>Name</span><input type="text" name="name" /></li>
		<li class="fix"><div class="sprite-pass ico"></div><span>Pass</span><input type="password" name="pass" /></li>
	</ul>
	<input type="submit" value="LOGIN" id="submit"/>
	</form>
	<p>→<a href="/regist" >CreateAccount</a></p>
</div>

</div><!-- wrap -->
</body>
</html>
