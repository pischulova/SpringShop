<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="/WEB-INF/layout/header.jsp"%>

<div class="body">
    <%@include file="/WEB-INF/layout/side_menu.jsp"%>
    <div class="content">
        <c:if test="${not empty message}">
            <p><spring:message code="${message}"/></p>
        </c:if>

        <%--<c:choose>--%>
            <%--<c:when test="${pageContext.response.locale == 'en'}">--%>
                <%--<c:set var="countryList" value="${countryListEN}"/>--%>
            <%--</c:when>--%>
            <%--<c:otherwise>--%>
                <%--<c:set var="countryList" value="${countryListRU}"/>--%>
            <%--</c:otherwise>--%>
        <%--</c:choose>--%>

        <%--<form action="/auth" method="post">--%>
        <%--<p>--%>
        <%--<form:select path="countryItems">--%>
        <%--<form:option value="NONE" label="----------" />--%>
        <%--<form:options items="${countryList}"/>--%>
        <%--</form:select>--%>

        <%--</p>--%>
        <%--</form>--%>
        <h1><fmt:message key="type_country"/></h1>
        <form:form method="post" commandName="chooseCountryForm" action="/flights">
            <spring:message code="search" var="submitText"/>

            <form:input type="text" path="countryName"/>
            <input type="submit" value="${submitText}">
        </form:form><br>

        <table width="100%">
            <c:if test="${not empty flightList}">
                <thead>
                <tr>
                    <th><fmt:message key="country"/></th>
                    <th><fmt:message key="destination"/></th>
                    <th><fmt:message key="price"/></th>
                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <th></th>
                        </c:when>
                    </c:choose>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="flight" items="${flightList}">
                    <tr>
                        <c:choose>
                            <c:when test="${pageContext.response.locale == 'en'}">
                                <td><c:out value="${flight.country.nameEn}"/></td>
                            </c:when>
                            <c:otherwise>
                                <td><c:out value="${flight.country.nameRu}"/></td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${pageContext.response.locale == 'en'}">
                                <td><c:out value="${flight.nameEn}"/></td>
                            </c:when>
                            <c:otherwise>
                                <td><c:out value="${flight.nameRu}"/></td>
                            </c:otherwise>
                        </c:choose>

                        <td><c:out value="${flight.price}"/></td>

                        <c:choose>
                            <c:when test="${not empty sessionScope.user}">
                                <td>
                                    <c:if test="${sessionScope.user.userRole=='ADMIN'}">
                                        <a href="<c:url value="editflight/?id=${flight.id}"/>">
                                            <fmt:message key="edit"/>
                                        </a>
                                    </c:if>
                                    <c:if test="${sessionScope.user.userRole=='CLIENT'}">
                                        <a href="<c:url value="addtocart/?id=${flight.id}"/>">
                                            <fmt:message key="add_to_cart"/>
                                        </a>
                                    </c:if>
                                </td>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:forEach>
                </tbody>
            </c:if>
        </table>
    </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>