<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% System.out.println("sessionView.jsp -----"); %>    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sessionView.jsp</title>
</head>
<body>

<%--
	시작 file인 sessionTracking.jsp에서 생성된 세션에 데이터 저장해 놨음
	session.setAttribute("id", "user01");
	session.setAttribute("pw", "77");
 --%>
	1. ${sessionScope.id}  
	
	<br>
	<button onclick="location.href='sessiontracking/logout1'">HttpSession 로그아웃</button>
    <br>
    
    <button onclick="location.href='sessiontracking/logout2'">
    	@SessionAttributes 로그아웃
    </button>
    <br>
	
	
	세션에 저장된 데이터 확인 : ${sessionScope.cust.id}-${sessionScope.cust.name} 
	
	<br>	
	<a href="sessionTest.html">sessionTest.html로 이동</a>
	
	<br>
	<a href="sessiontracking/sessionview">Model로 저장된 세션 데이터 controller에서 확인</a>
	
</body>
</html>






