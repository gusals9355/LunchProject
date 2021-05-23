<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
	<div class="mybox row">
		<h2>닉네임 변경</h2><br>
		<form action="/user/edit/nickname" method="post" onsubmit="alert('변경되었습니다.')">
			<div>
				닉네임<input type="text" name="nickname" value="${userInfo.nickName }" required autofocus maxlength="10"> 
			</div>
			<div>
				<button type="button" class="cancel btn btn-secondary" onclick="againCheck('ojm','취소')">취소</button>
				<input type="submit" class="btn btn-info" value="변경">
			</div>
		</form>
	</div>
</div>