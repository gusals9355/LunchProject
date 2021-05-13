function verify(){
	var pw = document.getElementById("pw");
	var pw2 = document.getElementById("pw2");
	if(pw.value != pw2.value){
		alert('비밀번호를 확인해주세요');
		return false;
	}
}