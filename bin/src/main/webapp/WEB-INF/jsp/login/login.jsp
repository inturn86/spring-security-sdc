<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="EUC-KR">

<title>Insert title here</title>

</head>

<body>

	<h1>�α��� ������</h1>



	<form action="login" method="post">

		<table>

			<tr>

				<td>username</td>
				<td><input type="text" name="username"
					placeholder="username �Է�"></td>

			</tr>

			<tr>

				<td>password</td>
				<td><input type="password" name="password"
					placeholder="��й�ȣ �Է�"></td>

			</tr>

			<tr>

				<td colspan="2" align="right"><button type="submit">�α���</button></td>

			</tr>

		</table>
		<div>
			<p class="error">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
		</div>

	</form>

</body>

</html>