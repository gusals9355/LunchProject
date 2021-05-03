<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/boot/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/boot/main.css">

<title>abcd</title>
</head>
<body>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=05a3bed3cf436895037eb617468dc965"></script>

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
		<div id="map" style="width:500px;height:400px; margin-top: 50px;"></div>
		
		<div class="home">
			<button type="button" class="home btn btn-outline-warning" onclick="panTo()">Home</button>
		</div>
	</div>
	<div>
		<div><label>제목 : <input type="text" name="title"></label></div>
		<div><label>id : <input type="text" name="id" value="${anonymous }"> </label></div>
		<div><label>pw : <input type="password" name="pw"> </label></div>
		ip : ${ipAddress }
		<input class="sub" type="submit" value="등록">
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
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/boot/bootstrap.js"></script>
</body>
</html>