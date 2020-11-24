<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page contentType="text/html; charset=utf8"%>
<%@ page import="java.util.*"%>
<html>
<head>
<title>Seattle Library</title>
</head>
<body>
	<div class="authorization_head">
		<img class="mark" src="resources/img/logo.png" />
		<div class="logo">Seattle Library</div>
	</div>
	<h1>ホーム画面</h1>
	<form action="<%=request.getContextPath()%>/search" method="post">
		<input type="search" name="search" placeholder="キーワードを入力"> <input
			type="submit" name="submit" value="検索">
	</form>

<form action="addBook" method="get">
		<div style="padding:5px;">
			<input type="submit" value="書籍の追加">
		</div>
</form>
	<c:if test="${!empty resultMessage}">
		<div style="color: red; font-weight: bold;">${resultMessage}</div>
	</c:if>
	<div style="padding: 10px;">
		<!-- JSTLのCoreタグライブラリで繰り返し処理。items属性に集合の値、var属性に集合の要素を指定。先頭のcはPrefix属性で、taglibで任意の値を指定できる。-->
		<div class="content_body">
        	<c:if test="${!empty resultMessage}">
				<div style="color:red;font-weight: bold; ">${resultMessage}</div>
			</c:if>
			<div style="padding: 10px;">
				<c:forEach var="bookInfo" items="${bookList}">
 					<div class="books">
						<form method="post" action="<%=request.getContextPath()%>/details">
							<a href="javascript:void(0)" onclick="this.parentNode.submit();">
								<c:if test="${empty bookInfo.thumbnail}">
									<img src="resources/img/noImg.png" style="width:200px;height:300px">
								</c:if>
								<c:if test="${!empty bookInfo.thumbnail}">
									<img src="resources/thumbnails/${bookInfo.thumbnail}" style="width:200px;height:300px">
								</c:if>
								<input type="hidden" name="bookId" value=${bookInfo.bookId}>
							</a>
						</form>
						<p>題名:${bookInfo.title}</p>
						<p>著者：${bookInfo.author}</p>
						<p>出版社：${bookInfo.publisher}</p>
						<p>出版日：${bookInfo.description}</p>
						<c:if test="${bookInfo.status == '0'}">
							<div style="color: red">${bookInfo.status == '0'}</div>
						</c:if>
						<c:if test="${bookInfo.status == '1'}">
							<div style="color: red">貸出不可</div>
						</c:if>
						---------------------------------------------------------------------
					</div>
 				</c:forEach>
			</div>
        </div>
	</div>
</body>
</html>
