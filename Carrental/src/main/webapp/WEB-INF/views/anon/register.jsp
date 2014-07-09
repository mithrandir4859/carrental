<%@ include file="/WEB-INF/views/jspf/startOfPage.jspf" %>
<title><my:localized name="title.register" /></title>
</head>
<body>
	<%@ include file="/../WEB-INF/views/jspf/menu.jsp"%>
	<form method="post">
		<div class="middle">
			<my:localizedList name="errorList" />
		</div>
		<%@ include file="/../WEB-INF/views/jspf/questionnaire.jspf"%>
		<hr />
		<div class="middle">
			<input type="submit" value="<my:localized name="register.submit" />"
				class="large" />
		</div>
	</form>
<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>