<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/book.css"
	rel="stylesheet" type="text/css">
</head>
<style type="text/css">



</style>
<body>
<div id="top">
		<h1>图书管理</h1>
	</div>
	<div id="left">
		<ul>

			<li><a href="${pageContext.request.contextPath}/book">图书管理</a></li>
			<li><a
				href="${pageContext.request.contextPath }/book?us=us">商城</a></li>
			<li><a
				href="${pageContext.request.contextPath }/buy_book">购物车</a></li>
			<li><a
				href="${pageContext.request.contextPath}/book?action=logout">注销</a></li>

		</ul>
	</div>

	<div id="central">
		<div id="up">
		<h1>添加图书</h1>
			<form action="/Acc/book?action=insert" method="post">
				<p>
					书名：<input type="text" name="name">
				</p>
				<p>
					作者：<input type="text" name="author">
				</p>
				<p>
					价格：<input type="text" name="price">
				</p>
				<p>
					数量：<input type="number" name="qty">
				</p>
				<p>
					<input type="submit" value="提交"> <input type="reset"
						value="清空">
				</p>
			</form>
		</div>
	</div>
</body>
</html>