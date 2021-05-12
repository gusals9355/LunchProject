<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<body>
<jsp:include page="nav/${str }" flush="false"/>
<div>
	<form action="/findpw" method="post">
		<h1>비밀번호 찾기</h1>
		<label for="name">이름</label><input type="text" name="name" id="name" placeholder="이름"><br>
		<label for="email">이메일</label><input type="email" name="email" id="email" placeholder="이메일"><br>
		<label for="id">아이디</label><input type="text" name="id" id="id" placeholder="아이디를"><br>
		<label for="pw">비밀번호</label><input type="password" name="pw" id="pw" placeholder="비밀번호"><br>
		<label for="pwck">비밀번호 찾기</label><input type="password" name="pwck" id="pwck" placeholder="비밀번호 확인"><br>
		<button onclick="location.href='/main'">취소</button>
		<input type="submit" value="확인">
	</form>
</div>
</body>
</html>