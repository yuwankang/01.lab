<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
	import="model.domain.People, java.util.ArrayList, java.util.HashMap"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>EL tag</h2>
	
	step02. java 데이터 화면에 출력 API<br>
	<% //servlet에서 데이터 저장 후 이동된 jsp 관점에서 학습이라 가정
	   //test 데이터 구성 임시로 진행함
	   request.setAttribute("rd1", "요청 데이터 1");
	   session.setAttribute("sd1", "세션 데이터 1");
	   
	   //배열
	   String [] sa = {"d1","d2","d3"};
	   request.setAttribute("rd2", sa);
	   
	   // dto들을 세션에 저장
	   session.setAttribute("sd2", new People("연아", 30));
	   // dto를 ArrayList에 저장해서 세션에 또저장
	   
	   //dto들 HashMap에 저장해서 세션에 또 저장
	%>
	1. ${requestScope.rd1}-${sessionScope.sd1}<br>
	2. 배열 : ${requestScope.rd2[1]}<br>
	<%--out.println((People)session.getAttribute("sd2")).getName()--%>
	3. DTO : ${sessionScope.sd2.name}<br>
	
<%	//1
		//dto들을 ArrayList에 저장해서 세션에 또 저장
		//dto들 HashMap에 저장해서 세션에 또 저장
		People p1 = new People("연아", 30);
		People p2 = new People("재석", 50);
		People p3 = new People("은갈치", 40);
		
		ArrayList<People> al = new ArrayList<>();
		al.add(p1);
		al.add(p2);
		al.add(p3);
		
		request.setAttribute("rd3", al);
%>
4. DTO > ArrayList : ${requestScope.rd3[1].name}<br>

<%	//2
		HashMap<String, People> hm = new HashMap<>();
		hm.put("p1", p1); //연아
		hm.put("p2", p2);
		hm.put("p3", p3);
		request.setAttribute("rd4", hm);
%>
5. DTO > map : ${requestScope.rd4.p1.name}<br>

<%	//3
		HashMap<String, ArrayList<People>> hm2 = new HashMap<>();
		hm2.put("al", al);
		request.setAttribute("rd5", hm2);		
%>
6. DTO > ArrayList > HashMap : ${requestScope.rd5.al[2].name}<br>
	
	
	<br><hr><br>
	step01. 브라우저에 출력하는 tag : 연산 및 비교가 가능
	<br>

	<br>
	<hr>
	<br>

	<table border="1">
		<tr>
			<td>2+3</td>
			<td>${2+3}</td>
		</tr>
		<tr>
			<td>'a'=='a'</td>
			<td>${'a'=='a'}</td>
		</tr>
	</table>

</body>
</html>