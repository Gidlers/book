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
		<h1>Í¼Êé¹ÜÀí</h1>
	</div>

	<div id="left">
		<ul>
			<li class="bg" ><a href="${pageContext.request.contextPath}/book">Í¼Êé¹ÜÀí</a></li>
			<li class="bgs"><a href="${pageContext.request.contextPath }/book?us=us">ÉÌ³Ç</a></li>
			<li class="bg"><a href="${pageContext.request.contextPath }/buy_book">¹ºÎï³µ</a></li>
			<li class="bg"><a
				href="${pageContext.request.contextPath}/book?action=logout">×¢Ïú</a></li>

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
				ÊéÃû»òÕß×÷Õß£º<input type="text" name="name" value="${keyword }"> <input
					class="sub" type="submit" value="ËÑË÷">
			</form>
		</div>
		<div id="res" style="display: <%=dis%>;">
			<h1>'${keyword }'µÄËÑË÷½á¹û£º0</h1>
		</div>
		<div id="bo1" style="display: <%=display%>;">
			<table style="border: 1px solid;">
				<caption>Í¼ÊéÐÅÏ¢</caption>
				<tr>
					<td style="width: 100px;"><h4>±àºÅ</h4></td>
					<td style="width: 150px;"><h4>ÊéÃû</h4></td>
					<td style="width: 150px;"><h4>×÷Õß</h4></td>
					<td style="width: 60px;"><h4>¼Û¸ñ</h4></td>
					<td style="width: 70px;"><h4>Ê£ÓàÊýÁ¿</h4></td>
					<td style="width: 80px;"><h4>²Ù×÷</h4></td>
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
									href="${pageContext.request.contextPath}/buy_book?id=${book.id }&action=buy&us=us">¹ºÂò</a>
							</h4></td>
					</tr>
				</xh:forEach>

			</table>
			<div id="page">
				<p>
					<a class="bg"
						href="${pageContext.request.contextPath}/book?page=first&action=alterpage&us=us">Ê×Ò³</a>
					<a class="bg"
						href="${pageContext.request.contextPath}/book?page=up&action=alterpage&us=us">ÉÏÒ»Ò³</a>
					<span>${currPage }</span> <a class="bg"
						href="${pageContext.request.contextPath}/book?page=next&action=alterpage&us=us">ÏÂÒ»Ò³</a>
					<a class="bg"
						href="${pageContext.request.contextPath}/book?page=last&action=alterpage&us=us">Î²Ò³</a>
				</p>

			</div>
		</div>
	</div>
</body>
</html>