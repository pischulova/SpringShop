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
                <c:if test="${empty sessionScope.cart}">
                    <p><spring:message code="cart_empty"/></p>
                </c:if>
            </c:otherwise>
        </c:choose>

        <c:if test="${not empty sessionScope.cart}">
            <h2><fmt:message key="current_order"/></h2><br>
            <table width="100%">
                <thead>
                <tr>
                    <th><fmt:message key="product_name"/></th>
                    <th><fmt:message key="country"/></th>
                    <th><fmt:message key="price"/></th>
                    <th><fmt:message key="quantity"/></th>
                    <th></th>
                </tr>
                </thead>

                <c:set var="sum" value="${0}"/>
                <c:set var="number" value="${0}"/>
                <tbody class="striped">
                <c:forEach var="item" items="${sessionScope.cart.getFlights()}">
                    <tr>
                        <c:if test="${pageContext.response.locale == 'en'}">
                            <td><c:out value="${item.key.nameEn}" /></td>
                            <td><c:out value="${item.key.country.nameEn}" /></td>
                        </c:if>
                        <c:if test="${pageContext.response.locale == 'ru'}">
                            <td><c:out value="${item.key.nameRu}" /></td>
                            <td><c:out value="${item.key.country.nameRu}" /></td>
                        </c:if>

                        <td><c:out value="${item.key.price}" /></td>
                        <td><c:out value="${item.value}" /></td>
                        <td>
                            <a href="<c:url value="/remove_from_cart/${item.key.id}"/>">
                                <fmt:message key="remove"/>
                            </a>
                        </td>
                    </tr>
                    <c:set var="sum" value="${sum + item.key.price * item.value}"/>
                    <c:set var="number" value="${number + item.value}"/>
                </c:forEach>
                <tr>
                    <td><h2><fmt:message key="total"/></h2></td>
                    <td></td>
                    <td><h2><c:out value="${sum}"/></h2></td>
                    <td><h2><c:out value="${number}"/></h2></td>
                </tr>
                </tbody>
            </table>

            <form action="/flights">
                <p><input type="submit" value="<fmt:message key="continue_shopping"/>"></p>
            </form>
            <form action="/make_order" method="post">
                <p><input type="submit" value="<fmt:message key="make_order"/>"></p>
            </form>
        </c:if>

    </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>
