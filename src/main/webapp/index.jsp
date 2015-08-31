<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="style/index.css" type="text/css" />
	<title>Java EE</title>
</head>
<body>
 		<%if (request.getParameter("session") != null && request.getParameter("session").equals("0")) {
            session.invalidate();
            request.getSession(true);
        } %>

	<div id="wrapper">
		
		<div id="icon">
			<img alt="icon" src="img/JavaDuke.png" width="70" height="90">
		</div>
		
		<h1>Онлайн библеотека на Java</h1>
		
		<p>
			Добро пожаловать в онлайн библиотеку.
		</p>
		
		<section>
			<h1>Для входа введите свои данные:</h1>
			<table>
				<form action="viwe/main.jsp" method="POST">
					<tr><td><input type="text" name="username"></td></tr>
					<tr><td></td><td><input type="submit" name="submit" value="Войти"></td></tr>
				</form>
			</table>
		</section>
		
		<h3>Разработчик угадай КТО.</h3>
		
	</div>
</body>
</html>
