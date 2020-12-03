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
    <h3>게시글 작성</h3>
    <div style="padding : 30px;">
        <form method="POST" action="/boardPost">
          <div class="form-group">
            <label>제목</label>
            <input type="text" name="title" class="form-control" enctype="multipart/form-data">
          </div>
          <div class="form-group">
            <label>작성자</label>
            <input type="text" name="writer" class="form-control">
          </div>
          <div class="form-group">
            <label>비밀번호</label>
            <input type="text" name="password" class="form-control">
          </div>
          <div class="form-group">
            <label>내용</label>
            <textarea name="content" class="form-control" rows="5"></textarea>
          </div>
           <div class="form-group row">  <!-- 파일업로드 부분  -->
            <label for="inputFile" class="col-sm-2 col-form-label"><strong>첨부 파일</strong></label>
            <div class="col-sm-10">
                <div class="custom-file" id="inputFile">
                    <input name="file" type="file" class="custom-file-input" id="customFile">
                    <label class="custom-file-label" for="customFile">파일을 선택해 주세요.</label>
                </div>
            </div>
          </div>
          <button type="submit" class="btn btn-default">작성</button> 	
        </form>
    </div>
</body>
</html>


