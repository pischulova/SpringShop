<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>

<div class="side">
    <c:choose>
        <c:when test="${sessionScope.isAdmin=='true'}">
            <ul class="side-menu">
                <li><a href="/profile"><spring:message code="profile"/></a></li>
                <li><a href="javascript:document.orders.submit();"><spring:message code="orders"/></a></li>
                <li><a href="javascript:document.clients.submit();"><spring:message code="clients"/></a></li>
                <form action="/auth" method="POST" name="orders">
                    <input type="hidden" name="command" value="show_orders">
                </form>
                <form action="/auth" method="POST" name="clients">
                    <input type="hidden" name="command" value="show_clients">
                </form>
            </ul>
        </c:when>
        <c:when test="${sessionScope.isAdmin=='false'}">
            <ul class="side-menu">
                <li><a href="/profile"><spring:message code="profile"/></a></li>
                <li><a href="javascript:document.orders.submit();"><spring:message code="my_orders"/></a></li>
                <li><a href="/cart"><spring:message code="cart"/></a></li>
                <form action="/auth" method="POST" name="orders">
                    <input type="hidden" name="command" value="show_orders">
                </form>
            </ul>
        </c:when>
        <c:otherwise>
            <springForm:form commandName="userDTO" method="post" action="/user/login" class="login-form">
                <label for="login-name"><spring:message code="name"/></label>
                <input type="text" name="name" id="login-name" required><br/>
                <label for="login-pass"><spring:message code="pass"/></label>
                <input type="password" name="pass" id="login-pass" required><br/>
                <input type="hidden" name="command" value="login">
                <input type="submit" value="ok">
            </springForm:form>
        </c:otherwise>
    </c:choose>
</div>
