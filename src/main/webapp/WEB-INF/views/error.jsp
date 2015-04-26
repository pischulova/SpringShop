<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="/WEB-INF/layout/header.jsp"%>
<div class="body">
    <%@include file="/WEB-INF/layout/side_menu.jsp"%>
    <div class="content">

        <c:if test="${not empty error_message}">
            <p><spring:message code="${error_message}"/></p>
        </c:if>

        <c:if test="${empty error_message}">
            <p><spring:message code="page_not_found"/></p>
        </c:if>

    </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>