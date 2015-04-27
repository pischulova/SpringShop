<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>

<%@include file="/WEB-INF/layout/header.jsp"%>

<div class="body">
    <%@include file="/WEB-INF/layout/side_menu.jsp"%>
    <div class="content">
        <h1><fmt:message key="orders"/></h1>

        <c:if test="${not empty message}">
            <p><spring:message code="${message}"/></p>
        </c:if>

        <c:choose>
            <c:when test="${sessionScope.user.userRole=='ADMIN'}">
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
                        <c:forEach var="order" items="${ordersList.pageList}">
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
            </c:when>

            <c:when test="${sessionScope.user.userRole=='CLIENT'}">
                <c:choose>
                    <c:when test="${empty ordersList}">
                        <fmt:message key="no_orders"/>
                    </c:when>

                    <c:otherwise>
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
                                <c:forEach var="order" items="${ordersList.pageList}">
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
                    </c:otherwise>
                </c:choose>

            </c:when>

            <c:otherwise>
                <fmt:message key="login_needed"/>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>
