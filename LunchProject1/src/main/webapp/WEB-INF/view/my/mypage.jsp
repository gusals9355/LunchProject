<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 | 오늘 점심 뭐먹지?</title>
</head>
<body>
<jsp:include page="../nav/${str }" flush="false"/>
	<section class="content">
		<div class="container">
			<div class="row">
				<div class="mybox col">
					<h3>비밀번호 변경</h3>
					<p>주기적으로 비밀번호를 변경하여 계정을 보호하세요.</p>
					<button type="button" class="btn btn-danger">비밀번호 변경</button>
				</div>
				<div class="mybox col">
					<h3>정말 탈퇴하시겠습니까?</h3>
					<p>탈퇴 시 기존의 정보들이 절대 복구 되지 않습니다.</p>
					<button type="button" class="btn btn-danger" onclick="againCheck('user/remove_user','탈퇴')">탈퇴하기</button>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
