<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/boot/bootstrap.css">
<title>오늘 점심은?</title>
</head>
<body>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=05a3bed3cf436895037eb617468dc965"></script>
	
	<div id="map" style="width:500px;height:400px;"></div>

	<script>
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = { 
	        center: new kakao.maps.LatLng(35.86611866674244, 128.5938331533704), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };
	
	// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	</script>
	
	<script src="/js/boot/bootstrap.js"></script>
</body>
</html>