<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>本の追加</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/home.css" />" type="text/css">
	<link href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="resources/js/display.js" /></script>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="resources/js/thumbnail.js"></script>
</head>
<body style="margin-right:15px;">
<header>
	<div class="top"><a href="<%= request.getContextPath()%>/home" style="text-decoration:none;">
			<p style="margin-bottom:0; margin-right:10px;"><img src="resources/img/logo.png" class="icon" ></p>
			</a></div>
	<ul class="top-right">
	<form action="search" method="post" style="display:inline-block;">
		<input name="keyword" type="search" placeholder="検索..." class="search1" style="border:1px solid #b0c4de;" required><i class="fas fa-search"></i>
		<input type="submit" style="vertical-align:top; background:none; border:0; color:white;">
	</form>

		<a href="<%= request.getContextPath()%>/home" class="menu"><li>Home
		</li></a>
		<a href="<%= request.getContextPath()%>/" style="margin-left:8px;"><li>ログアウト</li></a>
	</ul>
</header>
	<div class="num">
		<p>書籍データの追加</p>
	</div>
	<div class="body">
		<form action="<%=request.getContextPath()%>/insertBook" method="post" enctype="multipart/form-data" id="data_upload_form">
			<div class="type">
				<ul  style="padding:0;">
					<li style="margin-top:20px;">書籍の写真</li>
					<li style="font-size:7px; background-color:gray; padding:0 10px; margin-left:55px; color:white;">任意</li>
				</ul>
	        	<div class="content_navi">
	        		<div class="preview">
						<img src="resources/img/noImg.png" style="width:100px">
              		</div>
             		<input type="file" accept="image/*" name="thumbnail" id="thumbnail">
				</div>
			</div>
			<div class="all" style="float:left; padding-left:20px; margin-top:20px;">
				<div class="addcontainer">
					<ul><li class="ti">タイトル</li><li class="care care2">必須</li></ul>
					<c:if test="${!empty bookInfo}">
						<input class="get" type="text" name="title" value="${bookInfo.title}" placeholder="タイトル" autocomplete="off" required><hr>
					</c:if>
					<c:if test="${empty bookInfo}">
						<input class="get" type="text" name="title" placeholder="タイトル" autocomplete="off" required><hr>
					</c:if>
				</div>
				<div class="addcontainer">
					<ul><li class="ti">著者名</li><li class="care care2">必須</li></ul>
					<c:if test="${!empty bookInfo}">
						<input class="get" type="text" name="author" value="${bookInfo.author}" placeholder="著者名" autocomplete="off" required><hr>
					</c:if>
					<c:if test="${empty bookInfo}">
						<input class="get" type="text" name="author" placeholder="著者名" autocomplete="off" required><hr>
					</c:if>
				</div>
				<div class="addcontainer">
					<ul><li class="ti">出版社</li><li class="care care2">必須</li></ul>
					<c:if test="${!empty bookInfo}">
						<input class="get" type="text" name="publisher" value="${bookInfo.publisher}" placeholder="出版社"><hr>
					</c:if>
					<c:if test="${empty bookInfo}">
						<input class="get" type="text" name="publisher" placeholder="出版社"><hr>
					</c:if>
				</div>
				<div class="addcontainer">
					<ul><li class="ti">出版日</li><li class="care care2">必須</li></ul>
					<c:if test="${!empty bookInfo}">
						<input class="get" type="text" name="publishDate" value="${bookInfo.publishDateStr}" placeholder="出版日"><hr>
					</c:if>
					<c:if test="${empty bookInfo}">
						<input class="get" type="text" name="publishDate" placeholder="出版日"><hr>
					</c:if>
				</div>
				<div class="addcontainer">
					<ul><li class="ti">説明文</li><li class="care care1">任意</li></ul>
					<c:if test="${!empty bookInfo}">
						<input class="get" type="text" name="description" value="${bookInfo.description}" placeholder="説明文"><hr>
					</c:if>
					<c:if test="${empty bookInfo}">
						<input class="get" type="text" name="description" placeholder="説明文"><hr>
					</c:if>
				</div>
			</div>
			<div>
				<button type="submit" style="padding:15px 90px; margin-top:60px; border-radius:30px; background-color:#4169e1; color:white; text-align:center; font-size:15px;">作成</button>
			</div>
		</form>
	</div>
</body>
</html>
