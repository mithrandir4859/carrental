<%@ include file="/../WEB-INF/views/jspf/startOfPage.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>Choose interval</title>
</head>
<body>
	<%@ include file="/../WEB-INF/views/jspf/menu.jsp"%>
	<p class="middle">First, please choose a time interval: </p>
	<form name="input" method="get" action="${appFolder }/user/vehicles">
		<table>
			<tr>
				<th></th>
				<th>Day</th>
				<th>Month</th>
				<th>Year</th>
			</tr>
			<tr>
				<th>Start</th>
				<td><input type="text" value="${day }" name="startDay" size="2" /></td>
				<td><input type="text" value="${month }" name="startMonth" size="2" /></td>
				<td><input type="text" value="${year }" name="startYear" size="4" /></td>
			</tr>
			<tr>
				<th>End</th>
				<td><input type="text" value="${day }" name="endDay" size="2" /></td>
				<td><input type="text" value="${month }" name="endMonth" size="2" /></td>
				<td><input type="text" value="${year }" name="endYear" size="4" /></td>
			</tr>
		</table>
		<hr />

		<div class="middle">
			<input type="submit" value="Search car" class="large" />
		</div>
	</form>





</body>
</html>