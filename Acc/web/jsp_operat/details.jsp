<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="xh"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ѡ��ͼ��</title>
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
		<h1>ͼ�����</h1>
	</div>

	<div id="left">
		<ul>
			<li class="bg"><a
				href="${pageContext.request.contextPath }/book">ͼ�����</a></li>
			<li class="bg"><a
				href="${pageContext.request.contextPath }/book?us=us">�̳�</a></li>
			<li class="bgs"><a
				href="${pageContext.request.contextPath }/buy_book">���ﳵ</a></li>
			<li class="bg"><a
				href="${pageContext.request.contextPath }/book?action=logout">ע��</a></li>

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
			<h1 >����ѡ�����鼮</h1>
		</div>
		<div id="tab" style="display: <%=display%>;">
			<h1>��ѡ��ͼ��</h1>
			<br>

			<table style="border: 1px solid;">

				<tr>
					<td style="width: 100px;">���</td>
					<td style="width: 150px;">����</td>
					<td style="width: 150px;">����</td>
					<td style="width: 60px;">�۸�</td>
					<td style="width: 70px;">���</td>
					<td style="width: 160px;">��������</td>
					<td style="width: 80px;" colspan="2">����</td>
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
							href="${pageContext.request.contextPath }/buy_book?id=${entry.key.id}&action=del">ɾ��
						</a></td>
						<td><a class="bg"
							href="${pageContext.request.contextPath }/buy_book?id=${entry.key.id}&action=pay">֧��
						</a></td>
					</tr>
				</xh:forEach>

				<tr>
					<td class="bg"><a
						href="${pageContext.request.contextPath }/buy_book?id=All&action=pay">ȫ��֧��
					</a></td>
					<td colspan="3">${money }</td>
					<td class="bg" colspan="3" style="background: #918597;"><a
						href="${pageContext.request.contextPath }/buy_book?action=clear">��չ��ﳵ</a></td>
				</tr>
			</table>
			<div id="page">
				<p>
					<a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=f&state=0&action=page">��ҳ</a>
					<a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=u&state=0&action=page">��һҳ</a>
					<span style="color: black;">${currPage1 }</span> <a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=n&state=0&action=page">��һҳ</a>
					<a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=l&state=0&action=page">βҳ</a>
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
			<h1>������ʷ�����¼</h1>
		</div>
		<div id="record" style="display: <%=record%>;">
			<div id="pay">
				<br>
				<h1>�ѹ�ͼ���¼</h1>
				<br>
				<table style="border: 1px solid;">

					<tr>
						<td style="width: 100px;">���</td>
						<td style="width: 150px;">����</td>
						<td style="width: 150px;">����</td>
						<td style="width: 60px;">�۸�</td>
						<td style="width: 80px;">��������</td>
						<td style="width: 80px;">����</td>
					</tr>

					<xh:forEach var="entry" items="${pay_cart }">
						<tr>
							<td>${entry.value.id }&nbsp;</td>
							<td><h4>${entry.value.name }&nbsp;</h4></td>
							<td><h4>${entry.value.author }&nbsp;</h4></td>
							<td>${entry.value.price }&nbsp;</td>
							<td>${entry.key.value }</td>
							<td class="bg"><a
								href="${pageContext.request.contextPath }/buy_book?id=${entry.key.id}&action=del">ɾ��
							</a></td>
						</tr>
					</xh:forEach>
				</table>
			</div>
			<div id="page">
				<p>
					<a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=f&state=1&action=page">��ҳ</a>
					<a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=u&state=1&action=page">��һҳ</a>
					<span style="color: black;">${currPage2 }</span> <a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=n&state=1&action=page">��һҳ</a>
					<a class="bg"
						href="${pageContext.request.contextPath}/buy_book?order=l&state=1&action=page">βҳ</a>
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