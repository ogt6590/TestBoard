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

		<li class="page-item"><a class="page-link" 
		onclick="location.href='/list_criteria?currentPageNo=${params.currentPageNo-1}'">Previous</a></li>

		<c:forEach var="i" begin="1" end="${pa.totalPageCount}" step="1"
			varStatus="status">
				
			<c:choose>

	    		<c:when test="${i eq params.currentPageNo}">
	        		<li class="page-item active"><a class="page-link" 
	        		onclick="location.href='/list_criteria?currentPageNo=${i}'">${i}</a></li> 
	    		</c:when>
 
	    		<c:otherwise>
	        		<li class="page-item"><a class="page-link" 
	        		onclick="location.href='/list_criteria?currentPageNo=${i}'">${i}</a></li>
	    		</c:otherwise>
 
			</c:choose>
				
		</c:forEach>

		<li class="page-item"><a class="page-link" 
		onclick="location.href='/list_criteria?currentPageNo=${params.currentPageNo+1}'">Next</a></li>
	</ul>

	<!-- 현재페이지  -->
	<h1>${cr.currentPageNo}</h1>
	<!-- 페이지당출력할 데이터갯수  -->
	<h1>${cr.recordsPerPage}</h1>
	<!-- 화면하단에 출력할 페이지사이즈 -->
	<h1>${cr.pageSize}</h1>
	<!-- 총데이터갯수 -->
	<h1>${pa.totalRecordCount}</h1>
	<!-- 전체 페이지 개수 -->
	<h1>${pa.totalPageCount}</h1>
	<!-- 첫페이지 번호 -->
	<h1>${pa.firstPage}</h1>
	<!-- 마지막 페이지번호 -->
	<h1>${pa.lastPage}</h1>


</body>
</html>


