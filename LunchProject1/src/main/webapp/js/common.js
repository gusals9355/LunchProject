function goPage(page){
	location.href='/'+page;
}

function againCheck(page, str) {
	if (confirm(`정말 ${str}하시겠습니까?`) == true){
	    goPage(page);
	}else{
	    return false;
	}
}
