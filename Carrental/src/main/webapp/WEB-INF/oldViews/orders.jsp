<%@ include file="/../WEB-INF/views/jspf/startOfPage.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Submit Order</title>
</head>
<body>

	<%@ include file="/../WEB-INF/views/jspf/menu.jsp"%>
	<c:set var="deleteOrderAction" value="${appFolder}/user/orderDelete" />

	<table class="withBorders">
		<tr>
			<th>OrderId</th>
			<sec:authorize access="hasRole('ADMIN')">
				<th>UserId</th>
			</sec:authorize>
			<th>VehicleId</th>
			<sec:authorize access="hasRole('USER')">
				<th>Model</th>
			</sec:authorize>
			<th>Start date</th>
			<th>End date</th>
			<th>OrderStatus</th>
			<sec:authorize access="hasRole('USER')">
				<td>
					<!-- empty cell for "cancel" option -->
				</td>
			</sec:authorize>
		</tr>
		<c:forEach var="order" items="${orderList}">
			<tr>
				<c:set var="vehicleId" value="${order.vehicleId }" />
				<c:set var="orderId" value="${order.orderId}" />
				
				<td>
					<sec:authorize access="hasRole('USER')" >
						${orderId }
					</sec:authorize>
					<sec:authorize access="hasRole('ADMIN')">
						<form action="${appFolder }/admin/editOrder">
							<input type="hidden" name="orderId" value="${orderId }" /> <input type="submit"
								value="${orderId }" />
						</form>
					</sec:authorize>
				</td>
				<sec:authorize access="hasRole('ADMIN')">
					<td>${order.userId }</td>
				</sec:authorize>
				<td>${vehicleId }</td>
				<sec:authorize access="hasRole('USER')">
					<td>${vehicleModels[vehicleId] }</td>
				</sec:authorize>
				<td>${order.startDate }</td>
				<td>${order.endDate }</td>
				<td>${order.orderStatus }</td>
				<sec:authorize access="hasRole('USER')">
					<td>
						<form action="${deleteOrderAction }" method="POST">
							<input type="hidden" value="${orderId }" name="orderId" /> <input
								type="submit" value="Cancel" />
						</form>
					</td>
				</sec:authorize>
			</tr>


		</c:forEach>
	</table>


</body>
</html>