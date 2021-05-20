<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보 | 오늘 점심 뭐먹지?</title>
</head>
<body>
<jsp:include page="../nav/${str }" flush="false"/>
	<section class="content">
		<div class="container">
			<div class="row">
				<div class="mybox">
					<h3>비밀번호 확인</h3>
					<p>회원님의 정보를 안전하게 보호하기 위해 비밀번호를 한번 더 확인합니다.</p>
					<div>
						${userInfo.nickName}님
						회원 | ${userInfo.rank} (${userInfo.point }xp) 
					</div>
					<form action="" method="post">
						<div>
							<input type="text" name="id" value="${userInfo.id }" readonly>
							<input type="password" name="pw" autofocus>
							<p>${msg }</p>
							<input type="submit" class="btn btn-danger" value="비밀번호 확인">
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>