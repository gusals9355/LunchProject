var mapContainer = document.getElementById('map'), 
    mapOption = {
        center: new kakao.maps.LatLng(35.86611866674244, 128.5938331533704), 
        level: 3
    };

var map = new kakao.maps.Map(mapContainer, mapOption); 

var store = document.getElementsByName('store');
var mapX = document.getElementsByName('mapX');
var mapY = document.getElementsByName('mapY');
//console.log(store[0].value);
//console.log(mapX[0].value);
//console.log(mapY[0].value);

for (var i = 0; i < store.length; i ++) {
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
		alert('성공했따 ㅆㅃ');
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
