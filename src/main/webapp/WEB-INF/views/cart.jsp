<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${userLocale}" />
<fmt:setBundle basename="resources.bundle"/>

<%@include file="/WEB-INF/layout/header.jsp"%>

<div class="body">
    <%@include file="/WEB-INF/layout/side_menu.jsp"%>
    <div class="content">
        <c:choose>
            <c:when test="${not empty sessionScope.user.cart}">
                <h1><fmt:message key="current_order"/></h1>
                <table width="100%">
                    <thead>
                        <tr>
                            <th><fmt:message key="product_name"/></th>
                            <th><fmt:message key="country"/></th>
                            <th><fmt:message key="price"/></th>
                            <th></th>
                        </tr>
                    </thead>

                    <c:set var="sum" value="${0}"/>
                    <tbody>
                        <c:forEach var="item" items="${sessionScope.user.cart}">
                            <tr>
                                <td><c:out value="${item.name}" /></td>
                                <td><c:out value="${item.country.name}" /></td>
                                <td><c:out value="${item.price}" /></td>
                                <td>
                                    <form action="/auth" method="post">
                                        <input type="submit" value="<fmt:message key="remove"/>">
                                        <input type="hidden" name="flightId" value="${item.id}">
                                        <input type="hidden" name="action" value="remove">
                                        <input type="hidden" name="command" value="change_cart">
                                    </form>
                                </td>
                            </tr>
                            <c:set var="sum" value="${sum + item.price}"/>
                        </c:forEach>
                        <tr>
                            <td><fmt:message key="total"/></td>
                            <td></td>
                            <td><c:out value="${sum}"/></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>

                <form action="/flights" method="post">
                    <p><input type="submit" value="<fmt:message key="continue_shopping"/>"></p>
                </form>
                <form action="/auth" method="post">
                    <p><input type="submit" value="<fmt:message key="make_order"/>"></p>
                    <input type="hidden" name="command" value="make_order">
                    <input type="hidden" name="sum" value="${sum}">
                </form>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${sessionScope.orderSent=='true'}">
                        <fmt:message key="order_sent"/>
                        <c:remove var="orderSent" scope="session"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="cart_empty"/>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>
