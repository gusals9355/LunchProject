<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>abcd</title>
</head>
<body>
<jsp:include page="nav/${str }" flush="false"/>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=05a3bed3cf436895037eb617468dc965&libraries=services"></script>
	<div>
		<div id="map" style="width:500px;height:400px;"></div>
		<div class="home">
			<button type="button" class="home btn btn-outline-warning" onclick="panTo()">Home</button>
			<button type="button" class="write btn btn-outline-warning" onclick="location.href='/write'">글 등록</button>
		</div>
	</div>
	<p id="result"> <!-- 위도 경도 정보 -->
<script src="/js/kakao.js"></script>
</body>
</html>