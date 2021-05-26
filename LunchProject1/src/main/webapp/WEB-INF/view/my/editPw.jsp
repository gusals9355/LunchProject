<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
	<div class="mybox row">
		<h2>비밀번호 변경</h2><br>
		<form action="/user/edit/pw?id=${param.id }" method="post" onsubmit="return verify('비밀번호 변경');">
			<div>
				비밀번호<input type="password" name="pw" id="pw" value="${param.pw}" required autofocus maxlength="20" placeholder="비밀번호"> 
				비밀번호 확인<input type="password" name="pw2" id="pw2" value="${param.pw }" required maxlength="20" placeholder="비밀번호확인"> 
			</div>
			<p>${msg }
			<div>
				<button type="button" class="cancel btn btn-secondary" onclick="againCheck('ojm','취소')">취소</button>
				<input type="submit" class="btn btn-info" value="변경">
			</div>
		</form>
	</div>
</div>
<script src="/js/join.js"></script>