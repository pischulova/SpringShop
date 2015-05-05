<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="/WEB-INF/layout/header.jsp"%>

<div class="body">
    <%@include file="/WEB-INF/layout/side_menu.jsp"%>
    <div class="content">
        <c:choose>
            <c:when test="${not empty message}">
                <p><spring:message code="${message}"/></p>
            </c:when>

            <c:otherwise>
                <c:if test="${sessionScope.user.userRole=='ADMIN'}">
                    <c:set var="linkBase" value="show_orders?search=${search}&"/>

                    <h2><fmt:message key="type_client_name"/></h2>
                    <form:form method="post" commandName="searchOrderForm" action="/show_orders">
                        <form:input type="text" path="name"/>
                        <input type="submit" value="<fmt:message key="search"/>"> <br>
                        <form:errors path="name" cssClass="error" />
                    </form:form><br>

                    <table width="100%">
                        <thead>
                        <tr>
                            <th><fmt:message key="order_id"/></th>
                            <th><fmt:message key="date"/></th>
                            <th><fmt:message key="client"/></th>
                            <th><fmt:message key="amount"/></th>
                            <th><fmt:message key="status"/></th>
                            <th></th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="order" items="${ordersList}">
                            <tr>
                                <td><c:out value="${order.id}"/></td>
                                <td><c:out value="${order.date}"/></td>
                                <td><c:out value="${order.user.name}"/></td>
                                <td><c:out value="${order.sum}"/></td>

                                <c:choose>
                                    <c:when test="${order.isApproved == true}">
                                        <td><fmt:message key="approved"/></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><fmt:message key="not_approved"/></td>
                                    </c:otherwise>
                                </c:choose>

                                <td>
                                    <a href="<c:url value="/order?id=${order.id}"/>">
                                        <fmt:message key="view"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>

                <c:if test="${sessionScope.user.userRole=='CLIENT'}">
                    <c:set var="linkBase" value="client_orders?"/>

                    <table width="100%">
                        <thead>
                        <tr>
                            <th><fmt:message key="order_id"/></th>
                            <th><fmt:message key="date"/></th>
                            <th><fmt:message key="amount"/></th>
                            <th><fmt:message key="status"/></th>
                            <th></th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="order" items="${ordersList}">
                            <tr>
                                <td><c:out value="${order.id}"/></td>
                                <td><c:out value="${order.date}"/></td>
                                <td><c:out value="${order.sum}"/></td>
                                <c:choose>
                                    <c:when test="${order.isApproved == true}">
                                        <td><fmt:message key="approved"/></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><fmt:message key="not_approved"/></td>
                                    </c:otherwise>
                                </c:choose>
                                <td>
                                    <a href="<c:url value="/order?id=${order.id}"/>">
                                        <fmt:message key="view"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>

                <jsp:include page="/WEB-INF/layout/paging.jsp">
                    <jsp:param name="link" value="${linkBase}"/>
                </jsp:include>

            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>
