<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Secret</title>
<link  href="/css/reset.css" rel="stylesheet" type="text/css" />
<link  href="/css/decorate.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="wrap" class="boxin-shadow spotlight">

<% 
	String name = (String) session.getAttribute("NAME");
 %>
 
 HELLO<%= name %>さん
 <br>
 <a href="/Logout">ログアウトする？</a>

</div><!-- wrap -->
</body>
</html>
