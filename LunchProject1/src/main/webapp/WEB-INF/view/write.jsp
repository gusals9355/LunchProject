<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/boot/bootstrap.css"> -->
<link rel="stylesheet" href="/css/main.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<title>abcd</title>
</head>
<body>
<jsp:include page="../view2/${str }" flush="true"/>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=05a3bed3cf436895037eb617468dc965&libraries=services"></script>
	
	<div>
		<div class="dropdown">
			<button class="btn btn-secondary dropdown-toggle" type="button" id="foodType" data-bs-toggle="dropdown" aria-expanded="false">
				Food Type
			</button>
			<ul class="dropdown-menu" aria-labelledby="foodType">
				<li><a class="dropdown-item" href="#">한식</a></li>
				<li><a class="dropdown-item" href="#">양식</a></li>
				<li><a class="dropdown-item" href="#">일식</a></li>
				<li><a class="dropdown-item" href="#">중식</a></li>
				<li><a class="dropdown-item" href="#">분식</a></li>
				<li><a class="dropdown-item" href="#">카페</a></li>
				<li><a class="dropdown-item" href="#">기타</a></li>
			</ul>
		</div>
			
	<div class="map_wrap">
	    <div id="map" style="width:500px;height:400px;position:relative;overflow:hidden;"></div>
		<p id="inputLng"></p> <!-- 위도 경도 정보 -->
	    <div id="menu_wrap" class="bg_white">
	        <div class="option">
	            <div>
	                <form onsubmit="searchPlaces(); return false;">
	                    키워드 : <input type="text" value="코리아it아카데미" id="keyword" size="15"> 
	                    <button type="submit">검색하기</button> 
	                </form>
	            </div>
	        </div>
	        <hr>
	        <ul id="placesList"></ul>
	        <div id="pagination"></div>
	    </div>
	</div>
		
		<div class="home">
			<button type="button" class="home btn btn-outline-warning" onclick="panTo()">Home</button>
		</div>
	</div>
	<div>
		<form action="/write" method="post" enctype="multipart/form-data">
		<!-- 
			<div class="star">
				<button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
					평점
				</button>
				<ul class="dropdown-menu" aria-labelledby="foodType">
				<c:forEach var="i" begin="1" end="5">
					<li><button class="dropdown-item" onclick="setStar(${i})">
						<%-- i개만큼 꽉찬 별 찍기 --%>
						<c:forEach var="j" begin="1" end="${i}"> 
							<i class="bi bi-star-fill"></i>
						</c:forEach>
						<%-- 5-i개만큼 빈별 찍기 --%>
						<c:forEach var="j" begin="2" end="5">
							<i class="bi bi-star"></i>
						</c:forEach>
						</button>
				</c:forEach>
	
				</ul>
				<input type="hidden" value="5" id="star">
			</div>
		 -->
		<div><label>id : <input type="text" name="id" value="${anonymous }"> </label></div>
		<div><label>pw : <input type="password" name="pw"> </label></div>
		<div><label>제목 : <input type="text" name="title" maxlength="30"></label></div>
		<div><label>후기 : <input type="text" name="content" maxlength="1000"></label></div>
		<div>평점 : <input type="text" name="star1" value="1"></div>
		<div><label>카테고리 : <input type="text" name="category" maxlength="1000"></label></div>
		<div><label>맵x : <input type="text" name="mapX" maxlength="1000"></label></div>
		<div><label>맵y : <input type="text" name="mapY" maxlength="1000"></label></div>
		<div><input type="file" name="file[]" value="파일찾기" accept=".jpg,.jpeg,.png" multiple="multiple"></div>
		<!-- TODO: 첨부파일 (사진 cos이용) -->
		
		ip : ${ipAddress }
		<input type="submit" value="등록">
		</form>
	</div>
	
<script src="/js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>