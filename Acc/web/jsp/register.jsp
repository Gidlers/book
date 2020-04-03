<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ע���˻�</title>
<link href="${pageContext.request.contextPath}/css/book.css"
	rel="stylesheet" type="text/css">
<style type="text/css">


#up {
	top: 100px;
	margin: 0 auto;
	width: 292px;
	height: 320px;
	position: relative;
}

#B1 {left��0px;
	position: absolute;
	width: 120px;
	bottom: 0px;
}

#B2 {
	bottom: 0px;
	right: 0px;
	position: absolute;
	width: 120px;
	position: absolute;
}

p {
	line-height: 35px;
}

#back {
	border: solid 1px #87CEFA;
	position: absolute;
	text-align: center;
	height: 30px;
	line-height: 30px;
	width: 100px;
	color: black;
}
span{
width:350px;
position:absolute;
color: red;}
</style>
</head>

<body>
	<div>
		<p>
			<a id="back" class="sub"
				href="${pageContext.request.contextPath}/jsp/login">����</a>
		</p>
	</div>
	<div id="register">

		<div id="up">
			<h1>ע���˻�</h1>
			<form id="from" method="post">
				<p>
					�˻���<span id="sp"></span>
				</p>
				<p>
					<input id="user" type="text" name="username">
				</p>
				<p>
					���룺<span id="info"></span>
				</p>
				<p>
					<input id="pass1" type="password" name="password1">
				</p>
				<p>ȷ�����룺</p>
				<p>
					<input id="pass2" type="password" name="password2">
				</p>
				<p>
					<input id="B1" class="sub" type="submit" value="�ύ"> <input
						id="B2" class="sub" type="reset" value="���">
				</p>
			</form>
		</div>
	</div>
</body>
<script>
	document.getElementById('from').onsubmit = function() {
		//��ȡ�û�����
		var user = document.getElementById("user");
		var sp = document.getElementById("sp");
		var info = document.getElementById("info");
		var pass1 = document.getElementById("pass1");
		var pass2 = document.getElementById("pass2");
		if (user.value == '') {
			sp.innerText = '�û�������Ϊ��!';
			return false;
		} else if (!/^([1-9a-zA-Z][0-9a-zA-Z]{4,15})$/.test(user.value)) {
			sp.innerText = '�û���5-16λ!�������Ƿ��ַ�'
			return false;
		} else if (pass1.value == '' || pass2.value == '') {
			sp.innerText='';
			info.innerText = '���벻��Ϊ��!';
			return false;
		} else if (!/^(?![a-zA-Z]+$)(?![0-9]+$).{6,20}$/.test(pass1.value)) {
			info.innerText = '�������ͬʱ�������ֺ�Ӣ�ģ�����6λ!';
			return false;
		} else if (pass1.value != pass2.value) {
			info.innerText = '�������벻һ��!';
			return false;
		} else {
			document.getElementById('from').action = "${pageContext.request.contextPath}/account?action=register";

		}

	}
</script>
</html>