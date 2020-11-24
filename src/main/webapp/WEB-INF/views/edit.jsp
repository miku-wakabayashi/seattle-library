<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=utf8" %>
<html>
<head>
	<title>書籍の編集</title>
	<link rel="stylesheet" href="resources/css/edit.css">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="resources/js/thumbnail.js"></script>
</head>
<body>
<div class="authorization_head">
    <img class="mark" src="resources/img/logo.png" />
    <div class="logo">Seattle Library</div>
</div>
	<a href="<%= request.getContextPath()%>/home" class="menu"><li>Home</li></a>
	<a href="<%= request.getContextPath()%>/addBook" class="menu-bar"><li>書籍追加</li></a>
	<a href="<%= request.getContextPath()%>/" style="margin-left:8px;"><li>ログアウト</li></a>
<h1>
	書籍の編集
</h1>
<form action="<%=request.getContextPath()%>/updateBook" method="post" enctype="multipart/form-data" id="data_upload_form">
	<div>タイトル：<input id="title" name="title" placeholder=${bookInfo.title} value=${bookInfo.title} ></div>
		<div>著者：<input id="author" name="author" placeholder=${bookInfo.author} value=${bookInfo.author}></div>
	<div>出版社：<input id="publisher" name="publisher" placeholder=${bookInfo.publisher} value=${bookInfo.publisher}></div>
	<div>説明文：<input id="description" name="description" placeholder=${bookInfo.description} value=${bookInfo.description}></div>
	<div>サムネイル：<br>
		<br>
		<div class="content_navi">
        	<div class="preview">
				<c:if test="${empty bookInfo.thumbnail}">
					<div>
						<img id="thumbnail" src="resources/img/noImg.png" style="width:250px; clear:both; height:370px; margin:15px 6px;">
					</div>
					<label class="upload_btn">
						<input type="file" name="file">ファイルを選択
					</label>
					<p id="thumbnali_Name">選択されていません</p>
				</c:if>
				<c:if test="${!empty bookInfo.thumbnail}">
					<div>
						<img id="thumbnail" src="resources/thumbnails/${bookInfo.thumbnail}" style="width:250px; clear:both; height:370px; margin:15px 6px;">
					</div>
					<label class="upload_btn">
						<input type="file" name="file">ファイルを選択
					</label>
					<p id="thumbnali_Name">${bookInfo.thumbnail}</p>
				</c:if>
             	</div>
            	<input type="file" accept="image/*" name="thumbnail" id="thumbnail" value=${bookInfo.thumbnail}>
		</div>
	</div>
　	<input type="hidden" id="bookId" name="bookId" value=${bookInfo.bookId}>
	<br>
	<input type="submit" value="編集完了">
</form>
<form method="post" action="<%=request.getContextPath()%>/deleteBook">
	<input type="hidden" name="bookId" value=${bookInfo.bookId}>
	<input type="submit" value="削除">
</form>


</body>
</html>
