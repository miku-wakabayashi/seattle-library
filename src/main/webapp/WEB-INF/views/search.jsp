<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=utf8" %>
<html>
<head>
	<title>Seattle Library</title>
</head>
<body>
<h1>
	ホーム画面
</h1>
<form action="<%=request.getContextPath()%>/search" method="post">
<input type="search" name="search" placeholder="キーワードを入力">
<input type="submit" name="submit" value="検索">
</form>

<form action="addBook" method="get">
		<div style="padding:5px;">
			<input type="submit" value="書籍の追加">
		</div>
</form>

	<c:if test="${!empty resultMessage}">
		<div style="color:red;font-weight: bold; ">${resultMessage}</div>
	</c:if>
	<div style="padding: 10px;">
		<c:forEach var="item" items="${items}"><th style="border: solid 1px #cbcbcb; width: 150px;">${item.get("id")}</th>
					<form method="post" action="<%=request.getContextPath()%>/detail">
					<input type="hidden" name="id" value=${item.get("id")}>
					<a href="javascript:void(0)" onclick="this.parentNode.submit()">${item.get("title")}</a>
					</form>
					${item.get("author")}<br>
					${item.get("publisher")}<br>
					${item.get("description")}<br>
					<img src="../team19/resources/img/${item.get('image')}" data-lightbox="image-1" width="100px">
					<a href="../team19/resources/img/${item.get('image')}" data-lightbox="image-1">
					イメージを見る
					</a>
					<c:if test="${item.get('status') == 0}">
					<div style="color:red">貸出可能</div>
					</c:if>
					<c:if test="${item.get('status') == 1}">
					<div style="color:red">貸出不可</div>
					</c:if>
					<form method="post" action="<%=request.getContextPath()%>/rent">
					<input type="hidden" name="id" value=${item.get("id")}>
					<input type="hidden" name="status" value=${item.get("status")}>
					<a href="javascript:void(0)" onclick="this.parentNode.submit()">借りる</a>
					</form>
					<br><br>
			</c:forEach>
	</div>
<form action="top" method="POST">
		<div style="padding:5px;">
			<input type="submit" value="TOPへ戻る">
		</div>
</form>
</body>
</html>
