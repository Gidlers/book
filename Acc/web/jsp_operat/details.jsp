<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="xh"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ñ¡¹ºÍ¼Êé</title>
<link href="${pageContext.request.contextPath}/css/book.css"
	rel="stylesheet" type="text/css">
<style type="text/css">
#tab {
	height: 270px;
	width: 800px;
	position: relative;
}

#page {
	width: 800px;
	text-align: center;
	position: absolute;
	bottom: 0px;
}

#page input {
	top: 0px;
	font-size: 14px;
	background-color: white;
}

#record {
	height: 270px;
	width: 800px;
	position: relative;
}

button {
	font-size: 24px;
	border: none;
	line-height: 30px;
}
</style>
</head>
<body>
	<div id="top">
		<h1>Í¼Êé¹ÜÀí</h1>
	</div>

	<div id="left">
		<ul>
			<li class="bg"><a
				href="${pageContext.request.contextPath }/book">Í¼Êé¹ÜÀí</a></li>
			<li class="bg"><a
				href="${pageContext.request.contextPath }/book?us=us">ÉÌ³Ç</a></li>
			<li class="bgs"><a
				href="${pageContext.request.contextPath }/buy_book">¹ºÎï³µ</a></li>
			<li class="bg"><a
				href="${pageContext.request.contextPath }/book?action=logout">×¢Ïú</a></li>

		</ul>
	</div>
	<%
		String display = "none";
		String dis = "block";
		if (request.getSession().getAttribute("cart") == null) {
			display = "none";
		} else {
			display = "block";
		}
		if ("none".equals(display)) {
			dis = "block";
		} else {
			dis = "none";
		}
	%>

	<div id="central">
		<div id="tab" style="display: <%=dis%>;">
			<h1 >ÔÝÎÞÑ¡Ôñ¹ºÂòÊé¼®</h1>
		</div>
		<div id="tab" style="display: <%=display%>;">
			<h1>ÒÑÑ¡ÔñÍ¼Êé</h1>
			<br>

			<table style="border: 1px solid;">

				<tr>
					<td style="width: 100px;">±àºÅ</td>
					<td style="width: 150px;">ÊéÃû</td>
					<td style="width: 150px;">×÷Õß</td>
					<td style="width: 60px;">¼Û¸ñ</td>
					<td style="width: 70px;">¿â´æ</td>
					<td style="width: 160px;">¹ºÂòÊýÁ¿</td>
					<td style="width: 80px;" colspan="2">²Ù×÷</td>
				</tr>

				<xh:forEach var="entry" items="${cart }">
					<tr>
						<td>${entry.value.id }&nbsp;</td>
						<td><h4>${entry.value.name }&nbsp;</h4></td>
						<td><h4>${entry.value.author }&nbsp;</h4></td>
						<td>${entry.value.price }&nbsp;</td>
						<td>${entry.value.qty }&nbsp;</td>
						<td><a class="bg"
							href="${pageContext.request.contextPath }/buy_book?id=${entry.value.id}&action=add">+
						</a> &nbsp;${entry.key.value }&nbsp; <a
							href="${pageContext.request.contextPath }/buy_book?id=${entry.value.id}&action=sub">-</a>
						</td>
						<td><a class="bg"
							href="${pageContext.request.contextPath }/buy_book?id=${entry.key.id}&action=del">É¾³ý
						</a></td>
						<td><a class="bg"
							href="${pageContext.request.contextPath }/buy_book?id=${entry.key.id}&action=pay">Ö§¸¶
						</a></td>
					</tr>
				</xh:forEach>

				<tr>
					<td class="bg"><a
						href="${pageContext.request.contextPath }/buy_book?id=All&action=pay">È«²¿Ö§¸¶
					</a></td>
					<td colspan="3">${money }</td>
					<td class="bg" colspan="3" style="background: #918597;"><a
						href="${pageContext.request.contextPath }/buy_book?action=clear">Çå¿Õ¹ºÎï³µ</a></td>
				</tr>
			</table>
			<div id="page">
				<p>
					<a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=f&state=0&action=page">Ê×Ò³</a>
					<a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=u&state=0&action=page">ÉÏÒ»Ò³</a>
					<span style="color: black;">${currPage1 }</span> <a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=n&state=0&action=page">ÏÂÒ»Ò³</a>
					<a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=l&state=0&action=page">Î²Ò³</a>
				</p>

			</div>
		</div>

		<div style="width: 800px;">
			<hr>
		</div>
		<%
			String dis_pay = "none";
			String record = "block";
			if (request.getSession().getAttribute("pay_cart") == null) {
				dis_pay = "block";
				record = "none";
			} else {
				dis_pay = "none";
			}
		%>
		<div id="record" style="display: <%=dis_pay%>;">
			<h1>ÔÝÎÞÀúÊ·¹ºÂò¼ÇÂ¼</h1>
		</div>
		<div id="record" style="display: <%=record%>;">
			<div id="pay">
				<br>
				<h1>ÒÑ¹ºÍ¼Êé¼ÇÂ¼</h1>
				<br>
				<table style="border: 1px solid;">

					<tr>
						<td style="width: 100px;">±àºÅ</td>
						<td style="width: 150px;">ÊéÃû</td>
						<td style="width: 150px;">×÷Õß</td>
						<td style="width: 60px;">¼Û¸ñ</td>
						<td style="width: 80px;">¹ºÂòÊýÁ¿</td>
						<td style="width: 80px;">²Ù×÷</td>
					</tr>

					<xh:forEach var="entry" items="${pay_cart }">
						<tr>
							<td>${entry.value.id }&nbsp;</td>
							<td><h4>${entry.value.name }&nbsp;</h4></td>
							<td><h4>${entry.value.author }&nbsp;</h4></td>
							<td>${entry.value.price }&nbsp;</td>
							<td>${entry.key.value }</td>
							<td class="bg"><a
								href="${pageContext.request.contextPath }/buy_book?id=${entry.key.id}&action=del">É¾³ý
							</a></td>
						</tr>
					</xh:forEach>
				</table>
			</div>
			<div id="page">
				<p>
					<a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=f&state=1&action=page">Ê×Ò³</a>
					<a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=u&state=1&action=page">ÉÏÒ»Ò³</a>
					<span style="color: black;">${currPage2 }</span> <a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=n&state=1&action=page">ÏÂÒ»Ò³</a>
					<a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=l&state=1&action=page">Î²Ò³</a>
				</p>

			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function shop() {
		var shopid = document.getElementById('shop').style.display;
		if (shopid == "block") {
			document.getElementById('shop').style.display = "none";
		} else {
			document.getElementById('shop').style.display = "block";
		}

	}
	
	function record() {
		var payid = document.getElementById('paybook').style.display;

		if (payid == "block") {
			document.getElementById('paybook').style.display = "none";
		} else {
			document.getElementById('paybook').style.display = "block";
		}

	}
</script>
</html>