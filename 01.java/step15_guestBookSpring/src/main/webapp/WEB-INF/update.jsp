<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update.jsp</title>
</head>

<BODY>

<%--
	request.setAttribute("resultContent", gContent);
 --%>
<form name="updateForm" method="post" action="board">

    <input type="hidden" name="command" value="update">
    <input type='hidden' name='num' value="${requestScope.resultContent.num}">
    
	<table align="center" cellpadding="5" cellspacing="1" width="600" border="1">
    <tr>
        <td width="1220" height="20" colspan="2" bgcolor="#336699">
            <p align="center"><font color="white" size="3"><b>
            ${requestScope.resultContent.num}번 게시물 수정하기</b></font></p>
        </td>
    </tr>
    <tr>
        <td width="150" height="20">
            <p align="right"><b><span style="font-size:9pt;">작성자</span></b></p>
        </td>
        <td width="450" height="20"><b><span style="font-size:9pt;">
		<input type=text name="author" size="30"
		 value="${requestScope.resultContent.author}"></span></b></td>
    </tr>
    <tr>
        <td width="150" height="20">
            <p align="right"><b><span style="font-size:9pt;">전자메일</span></b></p>
        </td>
        <td width="450" height="20" ><b><span style="font-size:9pt;">
		<input type=text name="email" size="30"
		 value="${requestScope.resultContent.email}"></span></b></td>
    </tr>
    <tr>
        <td width="150" height="20">
            <p align="right"><b><span style="font-size:9pt;">제 목</span></b></p>
        </td>
        <td width="450" height="20"><b><span style="font-size:9pt;">
		<input type=text name="title" size="50"
		 value="${requestScope.resultContent.title}"></span></b></td>
    </tr>
    <tr>
        <td width="150" height="20" >
            <p align="right"><b><span style="font-size:9pt;">내 용</span></b></p>
        </td>
        <td width="450" height="20" >
        	<b>
	        	<span style="font-size:9pt;">
					<textarea name="content" cols="50" rows="10">${requestScope.resultContent.content}</textarea>
				</span>
			</b>
		</td>
    </tr>
    <tr>
        <td width="150" height="20" >
            <p align="right"><b><span style="font-size:9pt;">비밀번호</span></b></p>
        </td>
        <td width="450" height="20"><b><span style="font-size:9pt;"><input type=password name="password" size="12"> 
            (비밀번호가 맞아야 수정이 가능합니다.)</span></b></td>
    </tr>
    <tr>
        <td width="450" height="20" colspan="2" align="center">
        	<b><span style="font-size:9pt;">
				<input type="submit" value="수정하기"> 
				<input type="reset" value="다시쓰기">
			</span></b>
		</td>
    </tr>
</table>
</form>
<hr>
<div align=right><span style="font-size:9pt;">&lt;<a href="board">리스트로 돌아가기</a>&gt;</span></div>
</BODY>
</HTML>