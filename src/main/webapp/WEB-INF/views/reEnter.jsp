<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=utf8" %>
<html>
<head>
<title>書籍の追加</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="../team19/resources/js/upload.js"></script>
</head>
<div class="authorization_head">
    <img class="mark" src="../team19/resources/img/logo.png" />
    <div class="logo">Seattle Library</div>
</div>
<body>
<h1>
	書籍の追加
</h1>
<form enctype="multipart/form-data" method="post" action="<%=request.getContextPath()%>/insertBook">  <!-- insertBooks.java -->
		<div>タイトル</div><span id="false">入力に誤りがあります。もう一度入力してください。</span>
		<input type="text" id="title" name="title" />
		<div>著者</div>
		<input type="text" id="author" name="author" />
		<div>出版社</div>
		<input type="text" id="publisher" name="publisher" />
		<div>説明文</div>
		<input type="text" id="description" name="description" />
		<div>サムネイル</div><span id="false">入力に誤りがあります。もう一度入力してください。</span>
		<div style="padding:5px;">
		<input type="file" id="myfile" name="image" accept="image/*" />
			<input type="submit" value="追加">
		</div>
</form>


<form action="top" method="POST">
		<div style="padding:5px;">
			<input type="submit" value="TOPへ戻る">
		</div>
</form>
</body>
</html>
