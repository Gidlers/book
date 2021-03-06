<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="xh"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>选购图书</title>
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
		<h1>图书管理</h1>
	</div>

	<div id="left">
		<ul>
			<li class="bg"><a
				href="${pageContext.request.contextPath }/book">图书管理</a></li>
			<li class="bg"><a
				href="${pageContext.request.contextPath }/book?us=us">商城</a></li>
			<li class="bgs"><a
				href="${pageContext.request.contextPath }/buy_book">购物车</a></li>
			<li class="bg"><a
				href="${pageContext.request.contextPath }/book?action=logout">注销</a></li>

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
			<h1 >暂无选择购买书籍</h1>
		</div>
		<div id="tab" style="display: <%=display%>;">
			<h1>已选择图书</h1>
			<br>

			<table style="border: 1px solid;">

				<tr>
					<td style="width: 100px;">编号</td>
					<td style="width: 150px;">书名</td>
					<td style="width: 150px;">作者</td>
					<td style="width: 60px;">价格</td>
					<td style="width: 70px;">库存</td>
					<td style="width: 160px;">购买数量</td>
					<td style="width: 80px;" colspan="2">操作</td>
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
							href="${pageContext.request.contextPath }/buy_book?id=${entry.key.id}&action=del">删除
						</a></td>
						<td><a class="bg"
							href="${pageContext.request.contextPath }/buy_book?id=${entry.key.id}&action=pay">支付
						</a></td>
					</tr>
				</xh:forEach>

				<tr>
					<td class="bg"><a
						href="${pageContext.request.contextPath }/buy_book?id=All&action=pay">全部支付
					</a></td>
					<td colspan="3">${money }</td>
					<td class="bg" colspan="3" style="background: #918597;"><a
						href="${pageContext.request.contextPath }/buy_book?action=clear">清空购物车</a></td>
				</tr>
			</table>
			<div id="page">
				<p>
					<a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=f&state=0&action=page">首页</a>
					<a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=u&state=0&action=page">上一页</a>
					<span style="color: black;">${currPage1 }</span> <a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=n&state=0&action=page">下一页</a>
					<a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=l&state=0&action=page">尾页</a>
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
			<h1>暂无历史购买记录</h1>
		</div>
		<div id="record" style="display: <%=record%>;">
			<div id="pay">
				<br>
				<h1>已购图书记录</h1>
				<br>
				<table style="border: 1px solid;">

					<tr>
						<td style="width: 100px;">编号</td>
						<td style="width: 150px;">书名</td>
						<td style="width: 150px;">作者</td>
						<td style="width: 60px;">价格</td>
						<td style="width: 80px;">购买数量</td>
						<td style="width: 80px;">操作</td>
					</tr>

					<xh:forEach var="entry" items="${pay_cart }">
						<tr>
							<td>${entry.value.id }&nbsp;</td>
							<td><h4>${entry.value.name }&nbsp;</h4></td>
							<td><h4>${entry.value.author }&nbsp;</h4></td>
							<td>${entry.value.price }&nbsp;</td>
							<td>${entry.key.value }</td>
							<td class="bg"><a
								href="${pageContext.request.contextPath }/buy_book?id=${entry.key.id}&action=del">删除
							</a></td>
						</tr>
					</xh:forEach>
				</table>
			</div>
			<div id="page">
				<p>
					<a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=f&state=1&action=page">首页</a>
					<a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=u&state=1&action=page">上一页</a>
					<span style="color: black;">${currPage2 }</span> <a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=n&state=1&action=page">下一页</a>
					<a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=l&state=1&action=page">尾页</a>
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