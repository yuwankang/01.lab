<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 외부 library 추가 영역 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp2_ELDataValidate.jsp</title>
</head>
<body>
	<h2>EL data 존재 여부 검증</h2>
	<br><hr><br>
	
	1. ${requestScope.key1} 
	: 저장된 key자체가 없을때 즉 null인 경우 브라우저에선 blank 출력<br>
	
	<%-- jstl tag 조건식 활용 --%>
	<c:if test="${'a'=='a'}">
		2. 조건이 true인 경우에만 출력<br>
    </c:if>

	<c:if test="${'a' != 'a'}">
		3. 조건이 true인 경우에만 출력<br>
    </c:if>
	
	<%-- 4번 조건은 : 데이터가 null인 경우만 true --%>
	<c:if test="${empty requestScope.key1}">
		4. 비교가 true인 경우에만 출력<br>
	</c:if>	

	<%-- 5번 조건은 : 데이터가 null이 아닌 경우만 true --%>
	<c:if test="${not empty requestScope.key1}">
		5. 비교가 true인 경우에만 출력<br>
	</c:if>		
	
	<%-- if("a".equals("a")){
		 out.println("2. 조건이 true인 경우에만 출력
		} --%>
</body>
</html>