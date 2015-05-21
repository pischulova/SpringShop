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

                    <tbody class="striped">
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
                                <a href="<c:url value="/show_order/${order.id}"/>">
                                    <fmt:message key="view"/>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <jsp:include page="/WEB-INF/layout/paging.jsp">
                    <jsp:param name="link" value="client_orders?"/>
                </jsp:include>

            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>
