<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="navigation">
    <img class="logo" width="150" height="50" src="/resources/images/new-logo.png" alt=""/>
    <ul class="menu">
        <li class="menu-item"><a href="/"><spring:message code="home"/></a></li>
        <li class="menu-item"><a href="/flights"><spring:message code="flights"/></a></li>

        <sec:authorize access="isAuthenticated()">
            <li class="menu-item">
                <a href="j_spring_security_logout"><spring:message code="log_out"/></a>
            </li>
        </sec:authorize>
    </ul>

    <ul class="lang-menu">
        <li class="menu-item">
            <a href="<c:url value=""><c:param name = "lang" value="en"/></c:url>">
                <spring:message code="en"/>
            </a>
        </li>
        <li class="menu-item">
            <a href="<c:url value=""><c:param name = "lang" value="ru"/></c:url>">
                <spring:message code="ru"/>
            </a>
        </li>
    </ul>
</div>

