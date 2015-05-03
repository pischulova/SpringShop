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
                <h2><fmt:message key="order_details"/></h2><br>

                <c:if test="${sessionScope.user.userRole=='ADMIN'}">
                    <fmt:message key="client"/>: <c:out value="${order.user.name}"/><br><br>
                </c:if>

                <table width="100%">
                    <thead>
                    <tr>
                        <th><fmt:message key="order_id"/></th>
                        <th><fmt:message key="date"/></th>
                        <th><fmt:message key="amount"/></th>
                        <th><fmt:message key="status"/></th>
                    </tr>
                    </thead>
                    <tbody>
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
                    </tr>
                    </tbody>
                    <tr>
                </table>

                <table width="100%">
                    <thead>
                    <tr>
                        <th><fmt:message key="destination"/></th>
                        <th><fmt:message key="country"/></th>
                        <th><fmt:message key="price"/></th>
                        <th><fmt:message key="quantity"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${orderContents}">
                        <tr>
                            <c:if test="${pageContext.response.locale == 'en'}">
                                <td><c:out value="${item.flight.nameEn}"/></td>
                                <td><c:out value="${item.flight.country.nameEn}"/></td>
                            </c:if>
                            <c:if test="${pageContext.response.locale == 'ru'}">
                                <td><c:out value="${item.flight.nameRu}"/></td>
                                <td><c:out value="${item.flight.country.nameRu}"/></td>
                            </c:if>

                            <td><c:out value="${item.price}"/></td>
                            <td><c:out value="${item.quantity}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <c:if test="${sessionScope.user.userRole=='ADMIN'}">
                    <c:if test="${order.isApproved == false}">
                        <form action="/approve_order" method="post">
                            <input type="submit" value="<fmt:message key="approve"/>">
                            <input type="hidden" name="id" value="${order.id}">
                        </form>
                    </c:if>
                </c:if>
            </c:otherwise>
        </c:choose>

    </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>