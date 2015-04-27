<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="navigation">
    <img class="logo" width="150" height="50" src="/resources/new-logo.png" alt=""/>
    <ul class="menu">
        <li class="menu-item"><a href="/"><spring:message code="home"/></a></li>
        <li class="menu-item"><a href="/flights"><spring:message code="flights"/></a></li>
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <li class="menu-item">
                    <a href="/logout"><spring:message code="log_out"/></a><br>
                </li>
            </c:when>
        </c:choose>
    </ul>
    <ul class="lang-menu">
        <li class="menu-item">
                <a href="<c:url value="?lang=en"/>"><spring:message code="en"/></a>
        </li>
        <li class="menu-item">
            <a href="<c:url value="?lang=ru"/>"><spring:message code="ru"/></a>
        </li>
    </ul>
</div>

