<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/boot/bootstrap.css"> -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<title>${board.title}</title>
</head>
<body>
<jsp:include page="../nav/${str}" flush="false"/>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=05a3bed3cf436895037eb617468dc965&libraries=services"></script>
<div class="container">
	<div class="row layout">
	
		<div class="col left_layout"> <!-- 왼쪽 레이아웃 -->
			<div class="map_wrap"> <!-- 지도블럭 -->
				<div id="map" style="width:500px;height:400px;position:relative;overflow:hidden;"></div>
				<div id="menu_wrap" class="bg_white"> 
					<div class="option"></div>
					<ul id="placesList"></ul>
					<div id="pagination"></div>
				</div>
			</div>
			<div class="row"> <!-- 하단블럭 -->
				<p class="msg">${msg }</p>
				<div class="col category-div dropdown"> <!-- 카테고리 -->
					<button class="btn btn-secondary disabled" type="button" id="food">${board.category }</button>
				</div>
				<div class="col star-div dropdown"><!-- 평점 -->
					<button class="btn btn-secondary disabled" type="button" id="stars">${board.star }</button>
				</div>
			</div>
		</div>
		
		<div class="col right_layout"> <!-- 오른쪽 레이아웃 (주 폼태그) -->
			<div>
				${board.title }
			</div>
			<div class="danger-log"> <!-- danger log -->
				<p><strong>danger-log</strong></p>
			</div>
			<div>
				${board.content }
				<hr>
				<c:forEach var="item" items="${reple }">
					<div>
						${item.nickname } ${item.reple } ${item.star } ${item.reg_dt }
						
						<c:if test="${userInfo.id == item.id }">
							<button type="button" class="cancel btn btn-secondary" onclick="goPage('board/delReple?no=${param.no}&repleNo=${item.no}')">삭제</button>
							<button type="button" class="modify btn btn-info" onclick="goPage('board/modReple?no=${param.no}&repleNo=${item.no}')">수정</button>
						</c:if>
					</div>
				</c:forEach>
				
				<form action="/board/views?no=${param.no }" method="post">
					<div>
						<input type="text" name="reple" maxlength="500" size="50">
						<input type="text" name="star">
						<input type="submit" class="btn btn-success" value="등록" style="width: 50px"></input>
					</div>
				</form>
			</div>
			
			<c:if test="${userInfo.id == board.id }">
				<div class="row"> <!-- 하단 블럭 -->
					<div class="col"> <!-- 등록 -->
						<button type="button" class="cancel btn btn-secondary" onclick="goPage(delete)">삭제</button>
					</div>
					<div class="col">
						<button type="button" class="modify btn btn-info" onclick="goPage(modify)">수정</button>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</div>
<script src="/js/common.js"></script>
</body>
</html>