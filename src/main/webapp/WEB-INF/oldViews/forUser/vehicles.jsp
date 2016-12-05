<%@ include file="/../WEB-INF/views/jspf/startOfPage.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>Choose vehicle</title>
</head>
<body>
	<%@ include file="/../WEB-INF/views/jspf/menu.jsp"%>
	<p class="middle">Now choose a car: </p>
	<form method="GET" action="${appFolder }/user/order">
	
		<input type="hidden" value="${startMillis }" name="startMillis" />
		<input type="hidden" value="${endMillis }" name="endMillis"/>

		<table>
			<tr>
				<th></th>
				<th>Model</th>
				<th>Mileage</th>
				<th>Year</th>
			</tr>
			<c:forEach var="vehicle" items="${vehicleList}">
				<tr>
					<th><input type="radio" name="vehicleId"
						value="${vehicle.vehicleId }" /></th>
					<td>${vehicle.model }</td>
					<td>${vehicle.mileage }</td>
					<td>${vehicle.year }</td>
				</tr>
			</c:forEach>
		</table>
		<hr />
		
		<div class="middle">
				<input type="submit" value="Process"  class="large"/>
		</div>
	
	</form>
</body>
</html>