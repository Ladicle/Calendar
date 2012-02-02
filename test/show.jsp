<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="bigtable.Greeting" %>
<%@ page import="bigtable.PMF" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
	PersistenceManager pm = PMF.get().getPersistenceManager();
	String query = "select from "+Greeting.class.getName()+" order by date";
	List<Greeting> g = (List<Greeting>) pm.newQuery(query).execute();
	if(g.isEmpty()){
%>
	<p>No Article</p>
<%
	}else{
		for(Greeting gt: g){
%>
	<p><%= gt.getDate() %></p>
<%
			if(gt.getName() == null || gt.getName().equals("")){
%>
	<p>匿名</p>
<%
			}
%>
	
	<p><%= gt.getName() %></p>
	<div><%= gt.getContent() %></div>
	<hr />
<%
		}
	}
	pm.close();
%>
</body>
</html>