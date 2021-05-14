<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form onsubmit="searchPlaces(); return false;">
	<label>매장찾기 : <input type="text" value="코리아it아카데미" id="keyword" size="40"></label> 
	<button type="submit" style="display: none;">검색하기</button> 
</form>
<div id="map" style="width:100%;height:350px;"></div>
<div class="map_wrap"> <!-- 지도블럭 -->
	<div id="map" style="width:500px;height:400px;position:relative;overflow:hidden;"></div>
	<div id="menu_wrap" class="bg_white"> 
		<div class="option"></div>
		<ul id="placesList"></ul>
		<div id="pagination"></div>
	</div>
</div>


<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=05a3bed3cf436895037eb617468dc965&libraries=services"></script>
<script src="test.js"></script>
</body>
</html>