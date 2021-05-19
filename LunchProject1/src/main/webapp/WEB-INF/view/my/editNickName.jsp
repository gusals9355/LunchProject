<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<link rel="stylesheet" href="/css/main.css">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../nav/${str }" flush="false"/>
<div class="container">
	<div class="row">
		<div class="mybox col">
			<h2>닉네임 변경</h2><br>
			<form action="/editNickName" method="post" onsubmit="alert('변경되었습니다.')">
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
</div>

<script src="/js/common.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>