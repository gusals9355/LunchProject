<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<link rel="stylesheet" href="/css/main.css">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #ffecec; margin-bottom: 35px;">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="/ojm">list</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	      </ul>
	  		<div class="btn-group" role="group" aria-label="First group">
			    <button type="button" class="btn btn-outline-secondary" onclick="editNickName()">${userInfo.nickName }님</button>
			    <button type="button" class="btn btn-outline-secondary" onclick="goRank()">등급</button>
			    <button type="button" class="btn btn-outline-secondary" onclick="goRanking()">랭킹</button>
			    <button type="button" class="btn btn-outline-secondary" onclick="goMyPage()">내정보</button>
			    <button type="button" class="btn btn-outline-secondary"><fmt:formatNumber groupingUsed="true" value="${userInfo.point}"/>xp </button>
	  		</div>
	      <form class="d-flex" action="/logout" method="post">
	        <button class="btn btn-outline-success" type="submit">로그아웃</button>
	      </form>
	    </div>
	  </div>
	</nav>
<script src="/js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>