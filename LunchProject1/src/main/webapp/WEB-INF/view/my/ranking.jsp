<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<link rel="stylesheet" href="/css/main.css">
<title>랭킹 | 오늘 점심 뭐먹지?</title>
</head>
<body>
<jsp:include page="../nav/${str }" flush="false"/>
	<div class="container">
		<div class="row">
			<div class="mybox col-1">
				순위
			</div>
			<div class="mybox col-3">
				아이디
			</div>
			<div class="mybox col-2">
				등급
			</div>
			<div class="mybox col-2">
				포인트
			</div>
			<div class="mybox col-4">
				가입날짜
			</div>
		</div>
		<c:forEach var="i" begin="1" end="15">
			<div class="row">
				<div class="mybox col-1">
					${i }
				</div>
				<div class="mybox col-3">
					id
				</div>
				<div class="mybox col-2">
					등급
				</div>
				<div class="mybox col-2">
					포인트
				</div>
				<div class="mybox col-4">
					가입날짜
				</div>
			</div>
		</c:forEach>
	</div>
<script src="/js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>
