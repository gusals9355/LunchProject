<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/boot/bootstrap.css"> -->
<link rel="stylesheet" href="http://localhost:8080/css/main.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<title>abcd</title>

<script src="/js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=05a3bed3cf436895037eb617468dc965&libraries=services"></script>

</head>
<body>
<jsp:include page="../view2/${str }" flush="true"/>
	
<form action="/write" method="get">
	<%-- value : type --%>
	<div class="wrapper">
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
	</div>
	<%-- 지도 div --%>
	<div class="map_wrap">
	    <div id="map" style="width:500px;height:400px;position:relative;overflow:hidden;"></div>
	
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
	<%-- star --%>
	<div class="star">
		<button class="btn btn-secondary dropdown-toggle" type="button" id="star" data-bs-toggle="dropdown" aria-expanded="false">
				Food Type
			</button>
			<ul class="dropdown-menu" aria-labelledby="foodType">
			<c:foreach var="i" begin="1" end="5">
				<li><button class="dropdown-item" onclick="setStar(${i})">
					<%-- i개만큼 꽉찬 별 찍기 --%>
					<c:foreach var="j" begin="1" end="${i}"> 
						<i class="bi bi-star-fill"></i>
					</c:foreach>
					<%-- 5-i개만큼 빈별 찍기 --%>
					<c:foreach var="j" begin="2" end="5">
						<i class="bi bi-star"></i>
					</c:foreach>
					</button>
			</c:foreach>

			</ul>
		<input type="hidden" value="5" id="star">
	</div>
	<%-- value : id,pw,title,content,lng,lat --%>
	<div class="home">
		<button type="button" class="home btn btn-outline-warning" onclick="panTo()">Home</button>
	</div>
	<div>
		<div><label>id : <input type="text" name="id" value="${anonymous }"> </label></div>
		<div><label>pw : <input type="password" name="pw"> </label></div>
		<div><label>제목 : <input type="text" name="title"></label></div>
		<div><label>후기 : <input type="text" name="content"></label></div>
		<input type="hidden" id="lng" value="0">
	    <input type="hidden" id="lat" value="0">
		<!-- TODO: 첨부파일 (사진 cos이용) -->
		
		
		ip : ${ipAddress }
		<input class="sub" type="submit" value="등록">
	</div>
	<p id="result"></p> <!-- 위도 경도 정보 -->
</form>
</body>
</html>