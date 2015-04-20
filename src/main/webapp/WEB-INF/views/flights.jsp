<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="/WEB-INF/layout/header.jsp"%>

<div class="body">
    <%@include file="/WEB-INF/layout/side_menu.jsp"%>
    <div class="content">
        <c:if test="${sessionScope.flightChanged == true}">
            <fmt:message key="flight_updated"/><br><br>
            <c:remove var="flightChanged" scope="session"/>
        </c:if>

        <c:choose>
            <c:when test="${pageContext.response.locale == 'en'}">
                <c:set var="countryList" value="${countryListEN}"/>
            </c:when>
            <c:otherwise>
                <c:set var="countryList" value="${countryListRU}"/>
            </c:otherwise>
        </c:choose>

        <h1><fmt:message key="choose"/></h1>
        <form action="/auth" method="post">
            <p>
                <form:select path="countryItems">
                    <form:option value="NONE" label="----------" />
                    <form:options items="${countryList}"/>
                    <%--<c:choose>--%>
                        <%--<c:when test="${pageContext.response.locale == 'en'}">--%>
                            <%--<form:options items="${countryListEN}"/>--%>
                        <%--</c:when>--%>
                        <%--<c:otherwise>--%>
                            <%--<form:options items="${countryListRU}"/>--%>
                        <%--</c:otherwise>--%>
                    <%--</c:choose>--%>

                </form:select>


                <%--<select name="country">--%>
                    <%--<option value="">-----</option>--%>
                    <%--<option value="<fmt:message key="Great_Britain"/>"><fmt:message key="Great_Britain"/></option>--%>
                    <%--<option value="<fmt:message key="Greece"/>"><fmt:message key="Greece"/></option>--%>
                    <%--<option value="<fmt:message key="Sri_Lanka"/>"><fmt:message key="Sri_Lanka"/></option>--%>
                    <%--<option value="<fmt:message key="Sweden"/>"><fmt:message key="Sweden"/></option>--%>
                <%--</select>--%>
                <%--<select name="city">--%>
                    <%--<option value="">-----</option>--%>

                    <%--<option value="<fmt:message key="London"/>"><fmt:message key="London"/></option>--%>
                    <%--<option value="<fmt:message key="Liverpool"/>"><fmt:message key="Liverpool"/></option>--%>
                    <%--<option value="<fmt:message key="Glasgow"/>"><fmt:message key="Glasgow"/></option>--%>
                    <%--<option value="<fmt:message key="Athens"/>"><fmt:message key="Athens"/></option>--%>
                    <%--<option value="<fmt:message key="Heraklion"/>"><fmt:message key="Heraklion"/></option>--%>
                    <%--<option value="<fmt:message key="Thessaloniki"/>"><fmt:message key="Thessaloniki"/></option>--%>
                    <%--<option value="<fmt:message key="Colombo"/>"><fmt:message key="Colombo"/></option>--%>
                    <%--<option value="<fmt:message key="Stockholm"/>"><fmt:message key="Stockholm"/></option>--%>
                    <%--<option value="<fmt:message key="Malmo"/>"><fmt:message key="Malmo"/></option>--%>
                <%--</select>--%>
                <%--<input type="hidden" name="command" value="show_flights">--%>
                <%--<input type="submit" value="ok">--%>
            </p>
        </form>

        <table width="100%">
            <c:if test="${not empty sessionScope.flightsList}">
                <thead>
                <tr>
                    <th><fmt:message key="country"/></th>
                    <th><fmt:message key="destination"/></th>
                    <th><fmt:message key="price"/></th>
                    <c:choose>
                        <c:when test="${sessionScope.user != null}">
                            <th></th>
                        </c:when>
                    </c:choose>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="flight" items="${sessionScope.flightsList}">
                    <tr>
                        <td><c:out value="${flight.country.name}"/></td>
                        <td><c:out value="${flight.name}"/></td>
                        <td><c:out value="${flight.price}"/></td>

                        <c:choose>
                            <c:when test="${sessionScope.user != null}">
                                <td>
                                    <c:if test="${sessionScope.isAdmin=='true'}">
                                        <form action="/auth" method="post">
                                            <input type="submit" value="<fmt:message key="edit"/>">
                                            <input type="hidden" name="flight" value="${flight.id}">
                                            <input type="hidden" name="command" value="find_flight">
                                        </form>
                                    </c:if>
                                    <c:if test="${sessionScope.isAdmin=='false'}">
                                        <form action="/auth" method="post">
                                            <input type="submit" value="<fmt:message key="add_to_cart"/>">
                                            <input type="hidden" name="flightId" value="${flight.id}">
                                            <input type="hidden" name="action" value="add">
                                            <input type="hidden" name="command" value="change_cart">
                                        </form>
                                    </c:if>
                                </td>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:forEach>
                </tbody>
                <c:remove var="flightsList" scope="session"/>
            </c:if>
        </table>
    </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>