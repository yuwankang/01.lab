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
	우리 ${requestScope.msg} --- ${param.id}---
</body>
</html>