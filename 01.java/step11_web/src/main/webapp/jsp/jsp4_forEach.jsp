<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="model.domain.People, java.util.ArrayList, java.util.HashMap"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp4_forEach.jsp</title>
</head>
<body>

<%	//1
		People p1 = new People("연아", 30);
		People p2 = new People("재석", 50);
		People p3 = new People("은갈치", 40);
		
		ArrayList<People> al = new ArrayList<>();
		al.add(p1);
		al.add(p2);
		al.add(p3);
		
		request.setAttribute("rd3", al);
%>
<c:forEach items="${requestScope.rd3}" var="p">
	${p.name}<br>
</c:forEach>
<%--
ArrayList<People> al = (ArrayList<People>)request.getAttribute("rd3");
for(People p : al){
	out.println(p.getName());
}
 --%>
</body>
</html>