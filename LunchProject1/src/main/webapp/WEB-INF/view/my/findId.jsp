<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>아이디 찾기</title>
</head>
<body>
<jsp:include page="nav/${str }" flush="false"/>
<div>
	<form action="/findid" method="get">
		<h1>아이디 찾기</h1>
		<label for="email">이메일</label><input type="email" name="email" id="email" placeholder="이메일을 입력해주세요"><br>
		<label for="id">아이디</label>
		<%-- 아이디 개수만큼 아이디를 출력!--%>
		<c:forEach var="_id" items="idList">
			<p id="_id">${_id}</p>
		</c:forEach>
		<button onclick="location.href='/main'">취소</button>
		<input type="submit" value="확인">
	</form>
</div>

</body>
</html>