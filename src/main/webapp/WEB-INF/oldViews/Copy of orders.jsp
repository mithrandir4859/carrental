<%@ include file="/../WEB-INF/views/jspf/startOfPage.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Submit Order</title>
</head>
<body>

	<%@ include file="/../WEB-INF/views/jspf/menu.jsp"%>
	<c:set var="deleteOrderAction" value="${appFolder}/user/orderDelete" />
	<table class = "withBorders">
		<tr>
			<th>OrderId</th>
			<th>VehicleId</th>
			<th>Model</th>
			<th>Start date</th>
			<th>End date</th>
			<th>OrderStatus</th>
			<td></td>
		</tr>
		<c:forEach var="order" items="${orderList}">
			<tr>
				<c:set var="vehicleId" value="${order.vehicleId }" />
				<c:set var="orderId" value="${order.orderId}" />
				<td>${orderId }</td>
				<td>${vehicleId }</td>
				<td>${vehicleModels[vehicleId] }</td>
				<td>${order.startDate }</td>
				<td>${order.endDate }</td>
				<td>${order.orderStatus }</td>
				<td>
					<form action="${deleteOrderAction }" method="POST">
						<input type="hidden" value="${orderId }" name="orderId" />
						<input type="submit" value="Cancel" />
					</form>
				</td>
			</tr>


		</c:forEach>
	</table>


</body>
</html>