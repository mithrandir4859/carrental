<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="my" uri="/WEB-INF/custom.tld" %>
<c:set var="appFolder" value="<%=request.getContextPath()%>" />

<div class="middle">
	<img src="/carrental/resources/logo.png" alt="Car Rental">
</div>
<br />
<div class="middle">

	<ul class="menu">
		<li><a href="${appFolder}"><my:localized name="menu.home" /></a></li>

		<security:authorize access="hasRole('USER')">
			<li><a href="${appFolder}/user/chooseOrderDates"><my:localized name="menu.makeOrder" /></a></li>
			<li><a href="${appFolder}/user/myOrders"><my:localized name="menu.myOrders" /></a></li>
			<li><a href="${appFolder}/user/passportInfo"><my:localized name="menu.passportInfo" /></a></li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a href="${appFolder}/anon/login"><my:localized name="menu.login" /></a></li>
			<li><a href="${appFolder}/anon/register"><my:localized name="menu.register" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('ADMIN')">
			<li><a href="${appFolder}/admin/orderSelector"><my:localized name="menu.selectOrders" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li><a href="${appFolder}/j_spring_security_logout"><my:localized name="menu.logout" /></a></li>
		</security:authorize>

	</ul>
</div>
<!-- 
<security:authorize access="isAuthenticated()">
	<p align="right">
		<my:localized name="menu.welcome" />, ${sessionScope.user.firstname} ${sessionScope.user.lastname }
	</p>
</security:authorize>
 -->
<hr />

<my:localizedList name="errorList" prefix="error." />