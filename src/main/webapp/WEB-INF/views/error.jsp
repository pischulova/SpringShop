<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="/WEB-INF/layout/header.jsp"%>
<div class="body">

    <div class="content">

        <c:choose>
            <c:when test="${not empty error_message}">
                <p><spring:message code="${error_message}"/></p>
            </c:when>

            <c:when test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
                <p><spring:message code="bad_login"/></p>
                <c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION"/>
            </c:when>

            <c:otherwise>
                <p><spring:message code="page_not_found"/></p>
            </c:otherwise>
        </c:choose>

    </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>