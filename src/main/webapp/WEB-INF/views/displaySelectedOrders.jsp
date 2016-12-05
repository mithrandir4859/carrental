<%@ include file="/WEB-INF/views/jspf/startOfPage.jspf" %>
<%@ page import="carrental.domain.*"%>
<title><my:localized name="title.displaySelectedOrders" /></title>
</head>
<body>

	<%@ include file="/../WEB-INF/views/jspf/menu.jsp"%>

	<table class="withBorders">
		<tr>
			<th width="140"><my:localized name="order.orderId" /></th>

			<security:authorize access="hasRole('ADMIN')">
				<th><my:localized name="user.userId" /></th>
			</security:authorize>

			<security:authorize access="hasRole('USER')">
				<th><my:localized name="vehicle.model" /></th>
			</security:authorize>

			<th width="170"><my:localized name="date.dates" /></th>
			<th><my:localized name="order.orderStatus" /></th>
			<security:authorize access="hasRole('USER')">
				<th><my:localized name="orderHistory.payment"/></th>
				<th><my:localized name="orderHistory.reason"/></th>
				<td>
					<!-- empty cell for "cancel" option -->
				</td>
			</security:authorize>
		</tr>

		<c:forEach var="order" items="${orderList}">
			<tr>
				<c:set var="vehicleId" value="${order.vehicleId }" />
				<c:set var="orderId" value="${order.orderId}" />

				<td>
				<security:authorize access="hasRole('USER')">
					<my:localized name="order.orderId" />: ${orderId }
					<br />
					<my:localized name="vehicle.vehicleId" />: ${vehicleId }
				</security:authorize>
					
				<security:authorize access="hasRole('ADMIN')">
					<form action="${appFolder }/admin/editOrder">
						<input type="hidden" name="orderId" value="${orderId }" />
						<my:localized name="order.orderId" />:
						<input type="submit" value="${orderId }" />
					</form>
					<my:localized name="vehicle.vehicleId" />: ${vehicleId }
				</security:authorize>
				</td>

				<security:authorize access="hasRole('ADMIN')">
					<td><form action="${appFolder }/admin/passportInfo" method="get">
					<input type="hidden" name="userId" value="${order.userId }" />
					<input type="submit" value="${order.userId }" />
					</form></td>
				</security:authorize>

				<security:authorize access="hasRole('USER')">
					<td>${vehicleMap[vehicleId].model }</td>
				</security:authorize>

				<td>
				<my:localized name="date.start" /> ${order.startDate}
				<br />
				<my:localized name="date.end" /> ${order.endDate }
				</td>
				
				<td><my:localized name="orderStatus.${order.orderStatus}" /></td>

				<security:authorize access="hasRole('USER')">
					<c:forEach var="orderHistory" items="${order.orderHistoryList }">
						<td>${orderHistory.payment }</td>
						<td>${orderHistory.reason }</td>
					</c:forEach>
					<td>
						<form action="${appFolder}/user/cancelOrder" method="POST">
							<input type="hidden" value="${orderId }" name="orderId" /> <input
								type="submit" value="<my:localized name="cancel"/>" />
						</form>
					</td>
				</security:authorize>
				
				
			</tr>

		</c:forEach>
	</table>
<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>