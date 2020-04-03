<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="gbk">
<title>登录书城</title>
<link href="${pageContext.request.contextPath}/css/book.css"
	rel="stylesheet" type="text/css">

<style type="text/css">
#up {
	border: solid 4px #87CEFA;
	top: 200px;
	margin: 0 auto;
	width: 370px;
	height: 270px;
	position: relative;
	top: 200px;
}

#acc {
	top: 70px;
	position: absolute;
}

#B2 {
	width: 140px;
}

#B1 {
	width: 140px;
}

#sel {
	font-size: 20px;
	border: none;
}

#info {
	color: red;
	height: 20px;
	position: relative;
}
</style>
</head>
<body>
	<%
		String v = (String) request.getSession().getAttribute("vars");
		String dis = null;
		String mess = null;
		if ("username".equals(v)) {
			dis = "block";
			mess = "账户不能为空";
			request.getSession().setAttribute("vars", null);
		} else if ("password".equals(v)) {
			dis = "block";
			mess = "密码不能为空";
			request.getSession().setAttribute("vars", null);
		} else if ("err".equals(v)) {
			dis = "block";
			mess = "账户和密码不匹配";
			request.getSession().setAttribute("vars", null);
		} else {
			dis = "none";
		}
	%>


	<div id="up">
		<h1>&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录</h1>
		<div id="bb">
			<form
				action="${pageContext.request.contextPath}/account?action=login"
				method="post">
				<div id="info" style="display:  <%=dis%>;"><%=mess%></div>
				<div id="acc">
					<p>
						账户：<input type="text" name="username">
					</p>
					<p>
						密码：<input type="password" name="password">
					</p>

					<p id="sel">
						<input type="radio" name="select" value="tb_user"
							checked="checked" />用户 <input type="radio" name="select"
							value="tb_admin" />管理员
					</p>
				</div>

				<p>
					<input id="B1" class="sub" type="submit" value="登录">
				</p>

			</form>
		</div>
		<div id="register">
			<a href="register.jsp"> <input class="sub" id="B2" type="submit" value="注册"></a>
		</div>
	</div>
</body>
</html>