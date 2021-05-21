<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
	<div class="row">
		<div class="mybox col-1">
			순위
		</div>
		<div class="mybox col-3">
			아이디
		</div>
		<div class="mybox col-2">
			등급
		</div>
		<div class="mybox col-2">
			포인트
		</div>
		<div class="mybox col-4">
			가입날짜
		</div>
	</div>
	<c:forEach var="i" items="${rankingList}" varStatus="status">
		<div class="row">
			<div class="mybox col-1">
				${pageNum + status.count}
			</div>
			<div class="mybox col-3">
				${i.nickName }(${i.id })
			</div>
			<div class="mybox col-2">
				${i.rank }
			</div>
			<div class="mybox col-2">
				${i.point }
			</div>
			<div class="mybox col-4">
				${i.reg_dt }
			</div>
		</div>
	</c:forEach>
	<c:forEach var="i" begin="1" end="${maxPage}">
		&nbsp;<a href="?page=${i}">${i}</a>&nbsp;
	</c:forEach>
</div>
