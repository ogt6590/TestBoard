<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content= "text/html; charset=UTF-8">
<!-- BootStrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<title>게시글 작성</title>
</head>
<body>
    <h3>게시글 수정</h3>
    <div style="padding : 30px;">
        <form method="POST" action="/modify">
          <div class="form-group">
          	<input type="hidden" name="board_num" value="${board.board_num}">
            <label>내용</label>
            <textarea name="content" class="form-control" rows="5" placeholder="${board.content}"></textarea>
          </div>
          <button type="submit" class="btn btn-default">수정</button>
        </form>
    </div>
</body>
</html>


