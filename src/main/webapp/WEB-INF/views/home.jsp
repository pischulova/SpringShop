<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="/WEB-INF/layout/header.jsp"%>

<div class="body">
    <%@include file="/WEB-INF/layout/side_menu.jsp"%>
    <div class="content">
        <%--<c:if test="${not empty sessionScope.user}">--%>
        <%--<p><fmt:message key="greeting"/> ${user.login}!</p>--%>
        <%--</c:if>--%>

        <c:if test="${not empty message}">
            <p><spring:message code="${message}"/></p>
        </c:if>
        <p><spring:message code="welcome_text"/></p>
    </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>