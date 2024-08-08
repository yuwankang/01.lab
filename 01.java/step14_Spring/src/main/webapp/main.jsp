<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% System.out.println("*** 출력시 해당 jsp 요청 확인"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main.jsp</title>
</head>
<body>
	WEB-INF 폴더가 아님<br> ${requestScope.msg} --- ${param.id}---
</body>
</html>