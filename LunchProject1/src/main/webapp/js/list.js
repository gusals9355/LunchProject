var mapContainer = document.getElementById('map'), 
    mapOption = {
        center: new kakao.maps.LatLng(35.86611866674244, 128.5938331533704), 
        level: 3
    };

var map = new kakao.maps.Map(mapContainer, mapOption); 


let store = document.getElementsByName('store');
let mapX = document.getElementsByName('mapX');
let mapY = document.getElementsByName('mapY');
let no = document.getElementsByName('no');
for (let i = 0; i < store.length; i ++) {

    var marker = new kakao.maps.Marker({
        map: map, 
        position: new kakao.maps.LatLng(mapX[i].value, mapY[i].value)
    });
    
    var infowindow = new kakao.maps.InfoWindow({
        content: '<div>'+store[i].value+'</div>'
    });
    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
	kakao.maps.event.addListener(marker, 'click', function(){
		location.href=`/detail?no=${no[i].value}`
	});
}
function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}
function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    };
}

function panTo(){ //home으로 이동
	var moveLatLon = new kakao.maps.LatLng(35.86611866674244, 128.5938331533704);

	map.setLevel(3);
	map.panTo(moveLatLon);
}