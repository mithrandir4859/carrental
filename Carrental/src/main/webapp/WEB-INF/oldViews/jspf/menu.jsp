<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cM"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%-- <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> --%>
<%-- <%@ page --%>
<%-- 	import="org.springframework.security.core.context.SecurityContextHolder"%> --%>

<!-- <c:url value="createOrder" var="createOrder" /> -->
<!-- <c:url value="admin" var="adminUrl" /> -->
<!-- <c:url value="logout" var="logoutUrl" /> -->

<cM:set var="appFolder" value="<%=request.getContextPath()%>" />

<div class="middle">
	<img src="/carrental/resources/logo.png" alt="Car Rental">
</div>
<br />
<div class="middle">
	<ul class="menu">
		<li><a href="${appFolder}">Home</a></li>

		<sec:authorize access="hasRole('USER')">
			<li><a href="${appFolder}/user/date">Make order</a></li>
			<li><a href="${appFolder}/user/orders">My orders</a></li>
			<li><a href="${appFolder}/user/account">My account</a></li>
		</sec:authorize>

		<sec:authorize access="isAnonymous()">
			<li><a href="${appFolder}/anon/login">Login</a></li>
			<li><a href="${appFolder}/anon/register">Register</a></li>
		</sec:authorize>

		<sec:authorize access="hasRole('ADMIN')">
			<li><a href="${appFolder}/admin/selectOrders">Select orders</a></li>
		</sec:authorize>

		<sec:authorize access="isAuthenticated()">
			<li><a href="${appFolder}/j_spring_security_logout">Logout</a></li>
		</sec:authorize>
	</ul>
</div>
<hr />
