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
	<form action="/findid" method="get">
		<h1>���̵� ã��</h1>
		<label for="email">�̸���</label><input type="email" name="email" id="email" placeholder="�̸����� �Է����ּ���"><br>
		<label for="id">���̵�</label>
		<%-- ���̵� ������ŭ ���̵� ���!--%>
		<c:forEach var="_id" items="idList">
			<p id="_id">${_id}</p>
		</c:forEach>
		<button onclick="location.href='/main'">���</button>
		<input type="submit" value="Ȯ��">
	</form>
</div>

</body>
</html>