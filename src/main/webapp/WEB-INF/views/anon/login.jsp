<%@ include file="/WEB-INF/views/jspf/startOfPage.jspf" %>
<title><my:localized name="title.login" /></title>
</head>
<body>
	<%@ include file="/../WEB-INF/views/jspf/menu.jsp"%>
	<form method="post" action="${appFolder }/j_spring_security_check">
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="middle">${msg}</div>
		</c:if>
		<table>
			<my:advancedInput label="questionnaire.email" />
			<my:advancedInput label="questionnaire.password" />
		</table>
		<hr />

		<div class="middle">
			<input type="submit" value="<my:localized name="login.submit" />"
				class="large" />
		</div>
	</form>
<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>