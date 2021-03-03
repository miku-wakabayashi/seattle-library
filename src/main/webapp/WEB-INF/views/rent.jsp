<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=utf8" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>ステータス確認</title>
</head>
<body>
<div class="authorization_head">
<form method="post" action="<%=request.getContextPath()%>/top">
    <a href="javascript:void(0)" onclick="this.parentNode.submit()">
    <img class="mark" src="../team19/resources/img/logo.png" />
    </a>
</form>
    <div class="logo">Seattle Library</div>
</div>

<div style="padding: 10px;">
<!-- JSTLのCoreタグライブラリで繰り返し処理。items属性に集合の値、var属性に集合の要素を指定。先頭のcはPrefix属性で、taglibで任意の値を指定できる。-->
			<c:forEach var="item" items="${items}">
			<c:if test="${item.get('status') == 1}">
					<div style="color:red">貸出完了</div>
					貸出日：${item.get('rent-at')}<br>
					今日の一ヶ月後：${expiration}
			</c:if>
			<c:if test="${item.get('status') == 2}">
					<div style="color:red">予約完了</div>
			</c:if>
			<br>
			<th style="border: solid 1px #cbcbcb; width: 150px;">${item.get("id")}</th>
					<form method="post" action="<%=request.getContextPath()%>/detail">
					<input type="hidden" name="id" value=${item.get("id")}>
					<a href="javascript:void(0)" onclick="this.parentNode.submit()">${item.get("title")}</a><br>
					</form>
					${item.get("author")}<br>
					${item.get("publisher")}<br>
					${item.get("description")}<br>
					<img src="../team19/resources/img/${item.get('image')}" data-lightbox="image-1" width="100px">
					<a href="../team19/resources/img/${item.get('image')}" data-lightbox="image-1">
					イメージを見る
					</a>
			</c:forEach>
</div>


<form action="top" method="POST">
		<div style="padding:5px;">
			<input type="submit" value="TOPへ戻る">
		</div>
</form>
</body>
</html>
