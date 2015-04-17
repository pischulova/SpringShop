<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${userLocale}" />
<fmt:setBundle basename="resources.bundle"/>

<%@include file="/WEB-INF/layout/header.jsp"%>

<div class="body">
    <%@include file="/WEB-INF/layout/side_menu.jsp"%>
    <div class="content">
        <h1><fmt:message key="orders"/></h1>

        <c:choose>
            <c:when test="${sessionScope.isAdmin=='true'}">
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
                        <c:forEach var="order" items="${sessionScope.ordersList}">
                            <tr>
                                <td><c:out value="${order.id}"/></td>
                                <td><c:out value="${order.date}"/></td>
                                <td><c:out value="${order.client.name}"/></td>
                                <td><c:out value="${order.amount}"/></td>
                                <td><c:out value="${order.isApproved}"/></td>
                                <td>
                                    <form action="/auth" method="post">
                                        <input type="submit" value="<fmt:message key="view"/>">
                                        <input type="hidden" name="orderId" value="${order.id}">
                                        <input type="hidden" name="command" value="show_orders">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>

            <c:when test="${sessionScope.isAdmin=='false'}">
                <c:choose>
                    <c:when test="${empty sessionScope.ordersList}">
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
                                <c:forEach var="order" items="${sessionScope.ordersList}">
                                    <tr>
                                        <td><c:out value="${order.id}"/></td>
                                        <td><c:out value="${order.date}"/></td>
                                        <td><c:out value="${order.amount}"/></td>
                                        <td><c:out value="${order.isApproved}"/></td>
                                        <td>
                                            <form action="/auth" method="post">
                                                <input type="submit" value="<fmt:message key="view"/>">
                                                <input type="hidden" name="orderId" value="${order.id}">
                                                <input type="hidden" name="command" value="show_orders">
                                            </form>
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
