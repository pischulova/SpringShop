<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="side">
    <sec:authorize access="isAnonymous()" var="isAnonymous"/>

    <c:if test="${isAnonymous || not empty SPRING_SECURITY_LAST_EXCEPTION}">
        <springForm:form method="post" action="j_spring_security_check" class="login-form">
            <label for="j_username"><spring:message code="username"/></label>
            <input id="j_username" name="j_username" type="text" required><br/>

            <label for="j_password"><spring:message code="pass"/></label>
            <input id="j_password" name="j_password" type="password" required><br/>

            <input type="submit" value="<spring:message code="log_in"/>">
            <a href="/register_user" class="sign-up"><spring:message code="sign_up"/></a><br>
        </springForm:form>
    </c:if>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <ul class="side-menu">
            <li><a href="/profile"><spring:message code="profile"/></a></li>
            <li><a href="/admin/show_orders"><spring:message code="orders"/></a></li>
            <li><a href="/admin/show_clients"><spring:message code="clients"/></a></li>
        </ul>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_CLIENT')">
        <ul class="side-menu">
            <li><a href="/profile"><spring:message code="profile"/></a></li>
            <li><a href="/client_orders"><spring:message code="my_orders"/></a></li>
            <li><a href="/show_cart"><spring:message code="cart"/></a></li>
        </ul>
    </sec:authorize>
</div>
