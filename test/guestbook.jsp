<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<form action="/sign" method="post">
	<div>Name<input type="text" name="author" /></div>
	<div>Content<textarea name="content" rows="3" cols="60"></textarea></div>
	<div><input type="submit" value="送信" /></div>
</form>
</body>
</html>