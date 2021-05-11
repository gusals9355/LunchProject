//write.jsp 파일 썸네일
var input = document.querySelector('.input');
var preview = document.querySelector('.preview');

input.addEventListener('change', updateImageDisplay);
  
function updateImageDisplay() {
	while(preview.firstChild) { 
	  preview.removeChild(preview.firstChild); //프리뷰의 모든 내용을 지움
	}

	const curFiles = input.files;
	if(curFiles.length === 0) { //선택된 파일이 없을때
	  const para = document.createElement('p');
	  para.textContent = '선택된 파일이 없습니다';
	  preview.appendChild(para);
	} else {
	  const list = document.createElement('ol');
	  preview.appendChild(list);

	  for(const file of curFiles) {
	    const listItem = document.createElement('li');
	    const para = document.createElement('p');
	    if(validFileType(file)) {
	      const image = document.createElement('img');
	      image.src = URL.createObjectURL(file);

	      listItem.appendChild(image);
	      listItem.appendChild(para);
	    } else {
	      para.textContent = '파일 타입이 올바르지 않습니다. 다시 확인해주세요.';
	      listItem.appendChild(para);
	    }

	    list.appendChild(listItem);
	  }
	}
}
  
const fileTypes = [
	'image/apng',
	'image/bmp',
	'image/gif',
	'image/jpeg',
	'image/pjpeg',
	'image/png',
	'image/svg+xml',
	'image/tiff',
	'image/webp',
	'image/x-icon'
];

function validFileType(file) {
	return fileTypes.includes(file.type);
}

function goMyPage(){
	location.href='/mypage';
}
function goRanking(){
	location.href='/ranking';
}
function goRank(){
	location.href='/rank';
}
function setType(type){
    document.getElementById('foodType').value = type;
} 

//join 비번 확인

function verify(){
	var pw = document.getElementById('pw');
	var pw2 = document.getElementById('pw2');
	if(pw.value != pw2.value){
		alert('비밀번호를 확인해주세요');
		console.log('비번틀림');
		return false;
	}
}
<<<<<<< HEAD
=======

function verify(){
	var pw = document.getElementById("pw");
	var pw2 = document.getElementById("pw2");
	if(pw.value != pw2.value){
		alert('비밀번호를 확인해주세요');
		return false;
	}
}
>>>>>>> branch 'main' of https://github.com/gusals9355/LunchProject.git
