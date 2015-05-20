<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@include file="/WEB-INF/layout/header.jsp"%>

<div class="body">
    <%@include file="/WEB-INF/layout/side_menu.jsp"%>
    <div class="content">
        <c:choose>
            <c:when test="${not empty message}">
                <p><spring:message code="${message}"/></p>
            </c:when>
            <c:otherwise>

                <table width="40%">
                    <thead class="small">
                    <tr>
                        <th><fmt:message key="order_number"/>: </th>
                        <th>${order.id}</th>
                    </tr>
                    </thead>
                    <tbody>

                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <tr>
                            <th><fmt:message key="client"/>: </th>
                            <th><c:out value="${order.user.name}"/></th>
                        </tr>
                    </sec:authorize>

                    <tr>
                        <th><fmt:message key="date"/>: </th>
                        <th><c:out value="${order.date}"/></th>
                    </tr>

                    <tr>
                        <th><fmt:message key="status"/>: </th>
                        <c:choose>
                            <c:when test="${order.isApproved == true}">
                                <th><fmt:message key="approved"/></th>
                            </c:when>
                            <c:otherwise>
                                <th><fmt:message key="not_approved"/></th>
                            </c:otherwise>
                        </c:choose>
                    </tr>

                    </tbody>
                </table>

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <c:if test="${order.isApproved == false}">
                        <form action="/admin/approve_order" method="post">
                            <input type="submit" value="<fmt:message key="approve"/>">
                            <input type="hidden" name="id" value="${order.id}">
                        </form> <br><br>
                    </c:if>
                </sec:authorize>

                <table width="100%">
                    <thead>
                    <tr>
                        <th><fmt:message key="destination"/></th>
                        <th><fmt:message key="country"/></th>
                        <th><fmt:message key="quantity"/></th>
                        <th><fmt:message key="price"/></th>
                    </tr>
                    </thead>
                    <tbody class="striped">
                    <c:set var="number" value="${0}"/>
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
                            <td><c:out value="${item.quantity}"/></td>
                            <td><c:out value="${item.price}"/></td>
                        </tr>
                        <c:set var="number" value="${number + item.quantity}"/>
                    </c:forEach>
                    <tr>
                        <td><h2><fmt:message key="total"/></h2></td>
                        <td></td>
                        <td><h2><c:out value="${order.sum}"/></h2></td>
                        <td><h2><c:out value="${number}"/></h2></td>
                    </tr>
                    </tbody>
                </table>

            </c:otherwise>
        </c:choose>

    </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>