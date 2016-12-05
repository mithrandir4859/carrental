<%@page import="carrental.domain.OrderStatus"%>
<%@ include file="/../WEB-INF/views/jspf/startOfPage.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Submit Order</title>
</head>
<body>
	<%@ include file="/../WEB-INF/views/jspf/menu.jsp"%>
	<form action="${appFolder }/admin/ordersByStatus">
		<table>
			<c:forEach var="orderStatus" items="<%=OrderStatus.values()%>">
				<tr>
					<th>${orderStatus}</th>
					<td><input type="checkbox" name="orderStatus" value="${orderStatus}" checked></td>
				</tr>
			</c:forEach>
		</table>
		<hr />
		<div class="middle">
			<input type="submit" class="large" value="Search" />
		</div>
	</form>

</body>
</html>