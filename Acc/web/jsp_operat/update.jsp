<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="gbk">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/book.css"
	rel="stylesheet" type="text/css">

<style type="text/css">
#up {
	text-align: center;
	margin: auto;
}
</style>
</head>
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
		<h1>修改图书</h1>
		<div id="up">
			<form action="${pageContext.request.contextPath}/book?action=update"
				method="post">
				<p>
					编号：<input type="text" name="id" value="${book.id }"
						readonly="readonly">
				</p>
				<p>
					书名：<input type="text" name="name" value="${book.name }">
				</p>
				<p>
					作者：<input type="text" name="author" value="${book.author }">
				</p>
				<p>
					价格：<input type="text" name="price" value="${book.price }">
				</p>
				<p>
					数量：<input type="number" name="qty" value="${book.qty }">
				</p>
				<p>
					<input type="submit" value="提交"> <input type="reset"
						value="复位">
				</p>
			</form>
		</div>
	</div>
</body>
</html>