<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/boot/bootstrap.css">
<title>abcd</title>
</head>
<body>
<%@include file="../view2/nav.jsp" %>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=05a3bed3cf436895037eb617468dc965&libraries=services"></script>
	<div>
		<div id="map" style="width:500px;height:400px;"></div>
		<div class="home">
			<button type="button" class="home btn btn-outline-warning" onclick="panTo()">Home</button>
			<button type="button" class="write btn btn-outline-warning" onclick="location.href='/write'">글 등록</button>
		
		</div>
	</div>
	<p id="result"> <!-- 위도 경도 정보 -->
	
	<script>
	var mapContainer = document.getElementById('map'), 
	    mapOption = { 
	        center: new kakao.maps.LatLng(35.86611866674244, 128.5938331533704), 
	        level: 3 
	    };
	
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	var mapTypeControl = new kakao.maps.MapTypeControl(); //일반, 스카이뷰 전환
	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
	
	var zoomControl = new kakao.maps.ZoomControl(); 
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
	
	kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
		
	    // 클릭한 위도, 경도 정보를 가져옵니다 
	    var latlng = mouseEvent.latLng;
	    
	    var message = '위도 ' + latlng.getLat() ;
	    message += '경도 ' + latlng.getLng() ;
	    
	    var resultDiv = document.getElementById('result'); 
	    resultDiv.innerHTML = message;
	    
	});
	
	function panTo(){
		var moveLatLon = new kakao.maps.LatLng(35.86611866674244, 128.5938331533704);
		
		map.panTo(moveLatLon);
	}
	
	</script>
	
	<script src="/js/boot/bootstrap.js"></script>
</body>
</html>