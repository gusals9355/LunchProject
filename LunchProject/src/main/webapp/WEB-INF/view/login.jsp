<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<link rel="stylesheet" href="http://localhost:8080/css/main.css">
<title>로그인 | 오늘 점심 뭐먹지?</title>
</head>
<body>
<%@include file="../view2/nav.jsp" %>
	<div id="container">
		<section class="content">
			<header class="content-title">
				<h1>로그인을 해주세요</h1>
				<p>아직 아이디가 없다면 회원가입을 해주세요</p>
			</header>
			<form action="/login" method="post">
			<div class="input_row">
				<input class="int" type="text" name="id" placeholder="아이디" maxlength="20">
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
				<a href="/join">아이디찾기 </a>	<span class="txt-bar">|</span>
				<a href="/join">비밀번호찾기 </a>
			</div>
		</section>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>
