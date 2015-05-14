<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>

<div class="side">

    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <springForm:form method="post" action="j_spring_security_check" class="login-form">
                <label for="j_username"><spring:message code="username"/></label>
                <input id="j_username" name="username" type="text" required><br/>

                <label for="j_password"><spring:message code="pass"/></label>
                <input id="j_password" name="pass" type="password" required><br/>

                <input type="submit" value="<spring:message code="log_in"/>">
                <a href="/user" class="sign-up"><spring:message code="sign_up"/></a><br>
            </springForm:form>
        </c:when>
        <c:when test="${sessionScope.user.userRole=='ADMIN'}">
            <ul class="side-menu">
                <li><a href="/profile"><spring:message code="profile"/></a></li>
                <li><a href="/show_orders"><spring:message code="orders"/></a></li>
                <li><a href="/show_clients"><spring:message code="clients"/></a></li>
            </ul>
        </c:when>
        <c:when test="${sessionScope.user.userRole=='CLIENT'}">
            <ul class="side-menu">
                <li><a href="/profile"><spring:message code="profile"/></a></li>
                <li><a href="/client_orders"><spring:message code="my_orders"/></a></li>
                <li><a href="/show_cart"><spring:message code="cart"/></a></li>
            </ul>
        </c:when>
    </c:choose>
</div>
