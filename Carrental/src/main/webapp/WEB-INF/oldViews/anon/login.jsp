<%@ include file="/../WEB-INF/views/jspf/startOfPage.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>Login</title>
</head>
<body>
	<%@ include file="/../WEB-INF/views/jspf/menu.jsp"%>

	<form name="input" action="/carrental/j_spring_security_check"
		method="post">

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="middle">${msg}</div>
		</c:if>

		<table>
			<tr>
				<th>E-mail</th>
				<td><input type="text" value="" name="email" />
			</tr>

			<tr>
				<th>Password</th>
				<td><input type="password" value="" name="password" />
			</tr>

		</table>
		<hr />

		<div class="middle">
			<input type="submit" value="Login" class="large"/>
		</div>
	</form>
</body>
</html>