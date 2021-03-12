<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
  <meta charset="UTF-8">
  <title>書籍の追加｜シアトルライブラリ｜シアトルコンサルティング株式会社</title>
  <link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP" rel="stylesheet">
  <link href="<c:url value="/resources/css/default.css" />" rel="stylesheet" type="text/css">
  <link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
  <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="resources/js/thumbnail.js"></script>
    <script src="resources/js/addBtn.js"></script>
</head>
<body class="wrapper">
  <header>
    <div class="left">
      <img class="mark" src="resources/img/logo.png" />
      <div class="logo">Seattle Library</div>
    </div>
    <div class="right">
      <ul>
        <li><a href="<%= request.getContextPath()%>/home" class="menu">Home</a></li>
        <li><a href="<%= request.getContextPath()%>/">ログアウト</a></li>
      </ul>
    </div>
  </header>
  <main>
		<h1>書籍の追加</h1>
			<div class="content_body add_book_content">
					<div>
						<span>書籍の画像</span>
						<span class="care care1">任意</span>
						<div class="book_thumnail1">
							<img class="book_noimg" src="resources/img/noImg.png">
						</div>
						<input type="file" id="thumbnail1" accept="image/*" name="thumbnail">
					</div>
				<div class="content_right">
					<div>
						 <c:if test="${!empty errorList}">
						 	<div class="error">
						 		<c:forEach var="errorInfo" items="${errorList}">
 						 			<ul>
						 				<li>
						 					<c:out value="${errorInfo.errorMessage}"/>
						 				</li>
						 			</ul>
						 		</c:forEach>
						 	</div>
						 </c:if>
						<span>書籍名</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="title1" name="title" value="${bookInfo.title}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="title1" name="title" autocomplete="off">
						</c:if>
					</div>
					<div>
						<span>著者名</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="author1" name="author" value="${bookInfo.author}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="author1" name="author" autocomplete="off">
						</c:if>
					</div>
					<div>
						<span>出版社</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="publisher1" name="publisher" value="${bookInfo.publisher}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="publisher1" name="publisher">
						</c:if>
					</div>
					<div>
						<span>出版日</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="publishDate1" name="publishDate" value="${bookInfo.publishDate}" placeholder="YYYYMMDD">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="publishDate1" name="publishDate" placeholder="YYYYMMDD">
						</c:if>
					</div>
					<div>
						<span>ISBN</span><span class="care care1">任意</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="isbn1" name="isbn" value="${bookInfo.isbn}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="isbn1" name="isbn">
						</c:if>
					</div>
					<div>
						<span>説明文</span><span class="care care1">任意</span>
						<c:if test="${!empty bookInfo}">
               <input type="text" id="description1" name="description" value="${bookInfo.description}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="description1" name="description">
						</c:if>
					</div>
					<input type="hidden" id="bookId1" name="bookId" value="${bookInfo.bookId}">
				</div>
			</div>
			<hr class="border_add">
			<div class="content_body add_book_content">
					<div>
						<span>書籍の画像</span>
						<span class="care care1">任意</span>
						<div class="book_thumnail2">
							<img class="book_noimg" src="resources/img/noImg.png">
						</div>
						<div>
							<input type="file" id="thumbnail2" accept="image/*" name="thumbnail">
						</div>
					</div>
				<div class="content_right">
					<div>
						 <c:if test="${!empty errorList}">
						 	<div class="error">
						 		<c:forEach var="errorInfo" items="${errorList}">
 						 			<ul>
						 				<li>
						 					<c:out value="${errorInfo.errorMessage}"/>
						 				</li>
						 			</ul>
						 		</c:forEach>
						 	</div>
						 </c:if>
						<span>書籍名</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="title2" name="title" value="${bookInfo.title}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="title2" name="title" autocomplete="off">
						</c:if>
					</div>
					<div>
						<span>著者名</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="author2" name="author" value="${bookInfo.author}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="author2" name="author" autocomplete="off">
						</c:if>
					</div>
					<div>
						<span>出版社</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="publisher1" name="publisher" value="${bookInfo.publisher}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="publisher1" name="publisher">
						</c:if>
					</div>
					<div>
						<span>出版日</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="publishDate2" name="publishDate" value="${bookInfo.publishDate}" placeholder="YYYYMMDD">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="publishDate2" name="publishDate" placeholder="YYYYMMDD">
						</c:if>
					</div>
					<div>
						<span>ISBN</span><span class="care care1">任意</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="isbn2" name="isbn" value="${bookInfo.isbn}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="isbn2" name="isbn">
						</c:if>
					</div>
					<div>
						<span>説明文</span><span class="care care1">任意</span>
						<c:if test="${!empty bookInfo}">
               <input type="text" id="description2" name="description" value="${bookInfo.description}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="description2" name="description">
						</c:if>
					</div>
					<input type="hidden" id="bookId2" name="bookId" value="${bookInfo.bookId}">
				</div>
			</div>
			<div> <hr class="border_add"> </div>
			<div class="content_body add_book_content">
					<div>
						<span>書籍の画像</span>
						<span class="care care1">任意</span>
						<div class="book_thumnail3">
							<img class="book_noimg" src="resources/img/noImg.png">
						</div>
						<input type="file" id="thumbnail3" accept="image/*" name="thumbnail">
					</div>
				<div class="content_right">
					<div>
						 <c:if test="${!empty errorList}">
						 	<div class="error">
						 		<c:forEach var="errorInfo" items="${errorList}">
 						 			<ul>
						 				<li>
						 					<c:out value="${errorInfo.errorMessage}"/>
						 				</li>
						 			</ul>
						 		</c:forEach>
						 	</div>
						 </c:if>
						<span>書籍名</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="title3" name="title" value="${bookInfo.title}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="title3" name="title" autocomplete="off">
						</c:if>
					</div>
					<div>
						<span>著者名</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="author3" name="author" value="${bookInfo.author}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="author3" name="author" autocomplete="off">
						</c:if>
					</div>
					<div>
						<span>出版社</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="publisher3" name="publisher" value="${bookInfo.publisher}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="publisher3" name="publisher">
						</c:if>
					</div>
					<div>
						<span>出版日</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="publishDate3" name="publishDate" value="${bookInfo.publishDate}" placeholder="YYYYMMDD">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="publishDate3" name="publishDate" placeholder="YYYYMMDD">
						</c:if>
					</div>
					<div>
						<span>ISBN</span><span class="care care1">任意</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="isbn3" name="isbn" value="${bookInfo.isbn}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="isbn3" name="isbn">
						</c:if>
					</div>
					<div>
						<span>説明文</span><span class="care care1">任意</span>
						<c:if test="${!empty bookInfo}">
               <input type="text" id="description3" name="description" value="${bookInfo.description}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="description3" name="description">
						</c:if>
					</div>
					<input type="hidden" id="bookId3" name="bookId" value="${bookInfo.bookId}">
				</div>
			</div>
			<div> <hr class="border_add"> </div>
			<div class="content_body add_book_content">
					<div>
						<span>書籍の画像</span>
						<span class="care care1">任意</span>
						<div class="book_thumnail4">
							<img class="book_noimg" src="resources/img/noImg.png">
						</div>
						<input type="file" id="thumbnail4" accept="image/*" name="thumbnail" id="thumbnail">
					</div>
				<div class="content_right">
					<div>
						 <c:if test="${!empty errorList}">
						 	<div class="error">
						 		<c:forEach var="errorInfo" items="${errorList}">
 						 			<ul>
						 				<li>
						 					<c:out value="${errorInfo.errorMessage}"/>
						 				</li>
						 			</ul>
						 		</c:forEach>
						 	</div>
						 </c:if>
						<span>書籍名</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="title4" name="title" value="${bookInfo.title}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="title4" name="title" autocomplete="off">
						</c:if>
					</div>
					<div>
						<span>著者名</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="author4" name="author" value="${bookInfo.author}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="author4" name="author" autocomplete="off">
						</c:if>
					</div>
					<div>
						<span>出版社</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="publisher4" name="publisher" value="${bookInfo.publisher}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="publisher4" name="publisher">
						</c:if>
					</div>
					<div>
						<span>出版日</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="publishDate4" name="publishDate" value="${bookInfo.publishDate}" placeholder="YYYYMMDD">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="publishDate4" name="publishDate" placeholder="YYYYMMDD">
						</c:if>
					</div>
					<div>
						<span>ISBN</span><span class="care care1">任意</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="isbn4" name="isbn" value="${bookInfo.isbn}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="isbn4" name="isbn">
						</c:if>
					</div>
					<div>
						<span>説明文</span><span class="care care1">任意</span>
						<c:if test="${!empty bookInfo}">
               <input type="text" id="description4" name="description" value="${bookInfo.description}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="description4" name="description">
						</c:if>
					</div>
					<input type="hidden" id="bookId4" name="bookId" value="${bookInfo.bookId}">
				</div>
			</div>
			<div> <hr class="border_add"> </div>
			<div class="content_body add_book_content">
					<div>
						<span>書籍の画像</span>
						<span class="care care1">任意</span>
						<div class="book_thumnail5">
							<img class="book_noimg" src="resources/img/noImg.png">
						</div>
						<input type="file" id="thumbnail5" accept="image/*" name="thumbnail" id="thumbnail">
					</div>
				<div class="content_right">
					<div>
						 <c:if test="${!empty errorList}">
						 	<div class="error">
						 		<c:forEach var="errorInfo" items="${errorList}">
 						 			<ul>
						 				<li>
						 					<c:out value="${errorInfo.errorMessage}"/>
						 				</li>
						 			</ul>
						 		</c:forEach>
						 	</div>
						 </c:if>
						<span>書籍名</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="title5" name="title" value="${bookInfo.title}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="title5" name="title" autocomplete="off">
						</c:if>
					</div>
					<div>
						<span>著者名</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="author5" name="author" value="${bookInfo.author}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="author5" name="author" autocomplete="off">
						</c:if>
					</div>
					<div>
						<span>出版社</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="publisher5" name="publisher" value="${bookInfo.publisher}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="publisher5" name="publisher">
						</c:if>
					</div>
					<div>
						<span>出版日</span><span class="care care2">必須</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="publishDate5" name="publishDate" value="${bookInfo.publishDate}" placeholder="YYYYMMDD">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="publishDate5" name="publishDate" placeholder="YYYYMMDD">
						</c:if>
					</div>
					<div>
						<span>ISBN</span><span class="care care1">任意</span>
						<c:if test="${!empty bookInfo}">
							<input type="text" id="isbn5" name="isbn" value="${bookInfo.isbn}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="isbn5" name="isbn">
						</c:if>
					</div>
					<div>
						<span>説明文</span><span class="care care1">任意</span>
						<c:if test="${!empty bookInfo}">
               <input type="text" id="description5" name="description" value="${bookInfo.description}">
						</c:if>
						<c:if test="${empty bookInfo}">
							<input type="text" id="description5" name="description">
						</c:if>
					</div>
					<input type="hidden" id="bookId5" name="bookId" value="${bookInfo.bookId}">
				</div>
			</div>
			<form action="<%=request.getContextPath()%>/insertBook" method="post" enctype="multipart/form-data" id="data_upload_form">
				<div class="addBookBtn_box">
					<button type="submit" id="add-btn" class="btn_addBook">登録</button>
				</div>
			<input type="hidden" id="bookList" name="bookList">
		</form>
	</main>
</body>
</html>