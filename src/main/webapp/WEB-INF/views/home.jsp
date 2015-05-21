<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@include file="/WEB-INF/layout/header.jsp"%>

<div class="body">
    <%@include file="/WEB-INF/layout/side_menu.jsp"%>
    <div class="content">

        <sec:authorize access="isAuthenticated()">
            <p><fmt:message key="login_successful"/> ${sessionScope.user.username}.</p>
        </sec:authorize>

        <c:if test="${not empty message}">
            <p><spring:message code="${message}"/></p>
        </c:if>
        <p><spring:message code="welcome_text"/></p>
    </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>