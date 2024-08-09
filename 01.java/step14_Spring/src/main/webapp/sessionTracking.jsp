<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--
1. HttpSession과 Cookie 학습을 위한 시작 파일
2. 쿠키 데이터를 임시로 생성해서 client 시스템에  사전에 저장후 활용
	sessionTracking.jsp -> controller :redirect -> cookieView.jsp
		
3. jsp에는 내장 객체들 다수 존재
	- request, response, session...
 --%>   
 
<%
	//쿠키 생성 및 client 시스템에 저장
	//Cookie 는 jsp의 내장 객체가 아니기 때문에 필요에 의해 생성
	Cookie c1 = new Cookie("id", "tester");
	c1.setMaxAge(60*60);
	response.addCookie(c1);
	
	//세션-jsp 내장 객체, server 메모리에 데이터 저장
	session.setAttribute("id", "user01");
	session.setAttribute("pw", "77");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sessionTracking.jsp</title>
</head>
<body>
	<h3>Spring 을 활용한 상태유지 기술 개발 방법</h3>
	
	<br><hr><br>
	1. Cookie 학습<br>
	<a href="sessiontracking/cookietest">1-1. sessiontracking/cookietest</a>
	
	<br><hr><br>
	2. HttpSession 학습<br>
	<a href="sessiontracking/sessiontest">
		2-1. sessiontracking/sessiontest : HttpSession API 사용 - 로그인
	</a> <br>
	
	<a href="sessiontracking/sessiontest2">
		2-2. sessiontracking/sessiontest2 : Spring API - 로그인
	</a> <br>
	
	<a href="sessiontracking/sessiontest3?id=user01&name=khk&age=30">
		2-3. sessiontracking/sessiontest3?id=user01&name=khk&age=30 : 가입이라 가정, 
			세션 생성 및 Customer 데이터 저장, Spring API
	</a>
	<br>
	   
</body>
</html>
