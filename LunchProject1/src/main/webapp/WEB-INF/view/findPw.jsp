<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../view2/${str }" flush="false"/>
<div>
	<form action="/findpw" method="post">
		<h1>��й�ȣ ã��</h1>
		<label for="name">�̸�</label><input type="text" name="name" id="name" placeholder="�̸��� �Է����ּ���"><br>
		<label for="email">�̸���</label><input type="email" name="email" id="email" placeholder="�̸����� �Է����ּ���"><br>
		<label for="id">���̵�</label><input type="text" name="id" id="id" placeholder="���̵� �Է����ּ���"><br>
		<label for="pw">��й�ȣ</label><input type="password" name="pw" id="pw" placeholder="��й�ȣ"><br>
		<label for="pwck">��й�ȣ Ȯ��</label><input type="password" name="pwck" id="pwck" placeholder="��й�ȣ Ȯ��"><br>
		<button onclick="location.href='/main'">���</button>
		<input type="submit" value="Ȯ��">
	</form>
</div>
</body>
</html>