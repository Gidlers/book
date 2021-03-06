<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="xh"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/book.css"
	rel="stylesheet" type="text/css">
<style type="text/css">


</style>
</head>
<body>
	<div id="top">
		<h1>图书管理</h1>
	</div>

	<div id="left">
		<ul>
			<li class="bg" ><a href="${pageContext.request.contextPath}/book">图书管理</a></li>
			<li class="bgs"><a href="${pageContext.request.contextPath }/book?us=us">商城</a></li>
			<li class="bg"><a href="${pageContext.request.contextPath }/buy_book">购物车</a></li>
			<li class="bg"><a
				href="${pageContext.request.contextPath}/book?action=logout">注销</a></li>

		</ul>
	</div>

	<%
		String display = "block";
		String dis = "none";
		if (request.getSession().getAttribute("book") == null) {
			dis = "block";
			display = "none";
		} else {
			dis = "none";
			display = "block";
		}
	%>



	<div id="central">
		<div id="search">
			<form
				action="${pageContext.request.contextPath}/book?action=search&us=us"
				method="post">
				书名或者作者：<input type="text" name="name" value="${keyword }"> <input
					class="sub" type="submit" value="搜索">
			</form>
		</div>
		<div id="res" style="display: <%=dis%>;">
			<h1>'${keyword }'的搜索结果：0</h1>
		</div>
		<div id="bo1" style="display: <%=display%>;">
			<table style="border: 1px solid;">
				<caption>图书信息</caption>
				<tr>
					<td style="width: 100px;"><h4>编号</h4></td>
					<td style="width: 150px;"><h4>书名</h4></td>
					<td style="width: 150px;"><h4>作者</h4></td>
					<td style="width: 60px;"><h4>价格</h4></td>
					<td style="width: 70px;"><h4>剩余数量</h4></td>
					<td style="width: 80px;"><h4>操作</h4></td>
				</tr>

				<xh:forEach var="book" items="${book }">
					<tr>
						<td>${book.id }&nbsp;</td>
						<td><h4>${book.name }&nbsp;</h4></td>
						<td><h4>${book.author }&nbsp;</h4></td>
						<td>${book.price }&nbsp;</td>
						<td>${book.qty }&nbsp;</td>
						<td class="bg"><h4>
								<a
									href="${pageContext.request.contextPath}/buy_book?id=${book.id }&action=buy&us=us">购买</a>
							</h4></td>
					</tr>
				</xh:forEach>

			</table>
			<div id="page">
				<p>
					<a class="bg"
						href="${pageContext.request.contextPath}/book?page=first&action=alterpage&us=us">首页</a>
					<a class="bg"
						href="${pageContext.request.contextPath}/book?page=up&action=alterpage&us=us">上一页</a>
					<span>${currPage }</span> <a class="bg"
						href="${pageContext.request.contextPath}/book?page=next&action=alterpage&us=us">下一页</a>
					<a class="bg"
						href="${pageContext.request.contextPath}/book?page=last&action=alterpage&us=us">尾页</a>
				</p>

			</div>
		</div>
	</div>
</body>
</html>