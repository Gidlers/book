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
		<h1>ͼ�����</h1>
	</div>
	<div id="left">
		<ul>

			<li><a href="${pageContext.request.contextPath}/book">ͼ�����</a></li>
			<li><a
				href="${pageContext.request.contextPath }/book?us=us">�̳�</a></li>
			<li><a
				href="${pageContext.request.contextPath }/buy_book">���ﳵ</a></li>
			<li><a
				href="${pageContext.request.contextPath}/book?action=logout">ע��</a></li>

		</ul>
	</div>

	<div id="central">
		<div id="up">
		<h1>���ͼ��</h1>
			<form action="/Acc/book?action=insert" method="post">
				<p>
					������<input type="text" name="name">
				</p>
				<p>
					���ߣ�<input type="text" name="author">
				</p>
				<p>
					�۸�<input type="text" name="price">
				</p>
				<p>
					������<input type="number" name="qty">
				</p>
				<p>
					<input type="submit" value="�ύ"> <input type="reset"
						value="���">
				</p>
			</form>
		</div>
	</div>
</body>
</html>