<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<title>로그인 | 오늘 점심 뭐먹지?</title>
</head>
<body>
<jsp:include page="nav/${str }" flush="false"/>
	<div id="container">
		<section class="content">
			<header class="content-title">
				<h1>로그인을 해주세요</h1>
				<p>아직 아이디가 없다면 회원가입을 해주세요</p>
			</header>
			<form action="/login" method="post">
			<div class="input_row">
				<input class="int" type="text" name="id" placeholder="아이디" maxlength="20" value="${param.id }">
			</div>
			<div class="input_row">
				<input class="int" type="password" name="pw" placeholder="비밀번호" maxlength="20">
			</div>
			<div class="error">
				<p>${msg }</p>
			</div>
			
			<div class="input_row">
				<input class="btn btn-success" type="submit" value="로그인">
			</div>
			</form>
			<div class="info">
				<a href="/join" class="join">회원가입 </a> <span class="txt-bar">|</span>
				<a href="/join">아이디찾기 </a> <span class="txt-bar">|</span>
				<a href="/join">비밀번호찾기 </a>
			</div>
		</section>
	</div>
</body>
</html>
