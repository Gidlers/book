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
		<h1>�޸�ͼ��</h1>
		<div id="up">
			<form action="${pageContext.request.contextPath}/book?action=update"
				method="post">
				<p>
					��ţ�<input type="text" name="id" value="${book.id }"
						readonly="readonly">
				</p>
				<p>
					������<input type="text" name="name" value="${book.name }">
				</p>
				<p>
					���ߣ�<input type="text" name="author" value="${book.author }">
				</p>
				<p>
					�۸�<input type="text" name="price" value="${book.price }">
				</p>
				<p>
					������<input type="number" name="qty" value="${book.qty }">
				</p>
				<p>
					<input type="submit" value="�ύ"> <input type="reset"
						value="��λ">
				</p>
			</form>
		</div>
	</div>
</body>
</html>