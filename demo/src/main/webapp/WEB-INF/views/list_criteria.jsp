<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- BootStrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<title>게시글 목록</title>
</head>
<body>
	<h3>게시글 목록</h3>
	<button class="btn btn-primary" style="float: right;"
		onclick="location.href='/boardWrite'">작성</button>
	<table class="table">
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성날짜</th>
		</tr>
		<c:forEach var="board" items="${list}" varStatus="status">
			<tr onclick="location.href='/boardView?board_num=${board.board_num}'">
				<td><c:out value="${board.board_num}" /></td>
				<td><c:out value="${board.title}" /></td>
				<td><c:out value="${board.writer}" /></td>
				<td><c:out value="${board.date}" /></td>
			</tr>
		</c:forEach>
	</table>
	
	<ul class="pagination">
		<!-- disabled가 있으면 마우스 커서가 금지표시로 바뀐다. -->
		<li class=disabled><a href="#" aria-label="Previous"> <span
				aria-hidden="true">«</span>
		</a></li>
		<!-- 현재 페이지 -->
		<li class="active"><a href="#">1</a></li>
		<li><a href="#">2</a></li>
		<li><a href="#">3</a></li>
		<li><a href="#">4</a></li>
		<li><a href="#">5</a></li>
		<li><a href="#" aria-label="Next"> <span aria-hidden="true">»</span>
		</a></li>
	</ul>

</body>
</html>


