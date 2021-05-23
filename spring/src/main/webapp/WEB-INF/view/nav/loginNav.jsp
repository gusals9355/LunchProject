<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		    <button type="button" class="btn btn-outline-secondary" onclick="goPage('user/edit/nickname')">${userInfo.nickName }님</button>
		    <button type="button" class="btn btn-outline-secondary">${userInfo.rank }</button>
		    <button type="button" class="btn btn-outline-secondary" onclick="goPage('ranking')">랭킹</button>
		    <button type="button" class="btn btn-outline-secondary" onclick="goPage('user/pw_check')">내정보</button>
		    <button type="button" class="btn btn-outline-secondary"><fmt:formatNumber groupingUsed="true" value="${userInfo.point}"/>xp </button>
  		</div>
      <form class="d-flex" action="/logout" method="post">
        <button class="btn btn-outline-success" type="submit">로그아웃</button>
      </form>
    </div>
  </div>
</nav>