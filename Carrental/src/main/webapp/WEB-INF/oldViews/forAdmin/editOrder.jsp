<%@page import="carrental.domain.OrderStatus"%>
<%@ include file="/../WEB-INF/views/jspf/startOfPage.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Submit Order</title>
</head>
<body>
	<%@ include file="/../WEB-INF/views/jspf/menu.jsp"%>
	<h2>Edit order ${order.orderId }</h2>
	<table class="withBorders">
		<tr>
			<th>User</th>
			<th>Vehicle</th>
			<th>Dates</th>
		</tr>
		<tr>
			<td>${user.firstname } ${user.lastname }, phone: ${user.phone }</td>
			<td>${vehicle.model }</td>
			<td>Start: ${order.startDate }</td>
		</tr>
		<tr>
			<td>(email: ${user.email }, userId: ${user.userId })</td>
			<td>(mileage: ${vehicle.mileage }, year: ${vehicle.year }, vehicleId: ${vehicle.vehicleId })</td>
			<td>End: ${order.endDate }</td>
		</tr>
	</table>

	<form method="POST" class="middle">
		<input type="hidden" name="orderId" value="${order.orderId }" /> <select
			name="orderStatus">
			<c:forEach var="orderStatus" items="<%=OrderStatus.values()%>">
				<option value="${orderStatus }">${orderStatus }</option>
			</c:forEach>
		</select>
		<hr />
		<input type="submit" value="Update order status" class="large" />
	</form>

</body>
</html>