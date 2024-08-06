<%@ page contentType="text/html; charset=utf-8" %>

<%
	// session 변수로 참조하는 내장 객체 존재
	System.out.println(session.getId());
	
	//출력 내장 객체 존재 
	out.println("1 - "+ session.getAttribute("key1"));
%>
<br><hr><br>	
<%-- 출력 2번 --%>
2 - <%= session.getAttribute("key1") %>
<br><hr><br>		
<%-- jsp에서 자바코드는 최소화, 가장 권장하는 tag : EL --%>
3 - ${sessionScope.key1}

<br><hr><br>
<a href='/step11_web/logout'>logout</a>
		
		
	