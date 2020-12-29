<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<meta charset="UTF-8">
	<title>詳細</title>
	<link rel="stylesheet" href="resources/css/home.css">
	<link rel="stylesheet" href="resources/css/lightbox.css">
	<link href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="resources/js/lightbox.js" /></script>
</head>
<body>
<header>
	<div class="top"><a href="<%= request.getContextPath()%>/home" style="text-decoration:none;">
		<p style="margin-bottom:0; margin-right:10px;"><img src="resources/img/logo.png" class="icon" ></p>
		</a>
	</div>
	<ul class="top-right">
	<form action="search" method="post" style="display:inline-block;">
		<input name="keyword" type="search" placeholder="検索..." class="search1" style="border:1px solid #b0c4de;" required><i class="fas fa-search"></i>
		<input type="submit" style="vertical-align:top; background:none; border:0; color:white;">
	</form>
		<a href="<%= request.getContextPath()%>/home" class="menu"><li>Home</li></a>
		<a href="<%= request.getContextPath()%>/addBook" class="menu-bar"><li>書籍追加</li></a>
		<a href="<%= request.getContextPath()%>/" style="margin-left:8px;"><li>ログアウト</li></a>
	</ul>
</header>
<div class="num">
		<p>書籍の詳細</p>
	</div>
<div class="body" style="margin-top:50px; display:inline-block;">
		<a href="resources/thumbnails/${bookInfo.thumbnail}" data-lightbox="image-1">
			<c:if test="${empty bookInfo.thumbnail}">
				<img src="resources/img/noImg.png" style="width:250px; clear:both; height:370px; margin-top:20px;">
			</c:if>
			<c:if test="${!empty bookInfo.thumbnail}">
				<img src="resources/thumbnails/${bookInfo.thumbnail}" style="width:250px; clear:both; height:370px; margin-top:20px;">
			</c:if>
			<input type="hidden" name="bookId" value=${bookInfo.bookId}>
		</a>
		<div class="detabook">
			<p style="font-size:24px; font-weight:bold; margin-top:0;">題名：${bookInfo.title}</p>
			<p style="text-align:right;">著者名：${bookInfo.author}</p>
			<div class="editbutton">
				<form method="post" action="editBook">
					<button type="submit" value=${bookInfo.bookId} name="bookId" class="detabu">編集</button>
				</form>
				<form method="post" action="deleteBook">
					<button type="submit" value=${bookInfo.bookId} name="bookId" class="detabu" >削除</button>
				</form>
			</div>
		</div>
<button type="submit" style="padding:10px 90px; margin:60px auto; border-radius:30px; background-color:#ffa500; color:white; text-align:center; display:block; font-size:15px;">借りる</button>
</div>
</body>
</html>
