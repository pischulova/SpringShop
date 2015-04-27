<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>

<%@include file="/WEB-INF/layout/header.jsp"%>

<div class="body">
    <%@include file="/WEB-INF/layout/side_menu.jsp"%>
    <div class="content">
        <c:choose>
            <c:when test="${not empty message}">
                <p><spring:message code="${message}"/></p>
            </c:when>

            <c:otherwise>
                <h1><fmt:message key="orders"/></h1>
                <c:if test="${sessionScope.user.userRole=='ADMIN'}">
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
                </c:if>

                <c:if test="${sessionScope.user.userRole=='CLIENT'}">

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
                </c:if>

                <ul class="pages-menu">
                    <c:if test="${!ordersList.firstPage}">
                        <li class="menu-item"><a href="/show_orders?page=1"><fmt:message key="first"/></a></li>
                    </c:if>

                    <c:choose>
                        <c:when test="${pageNumber < 5}">
                            <c:forEach begin="1" end="${pageNumber}" var="p">
                                <li class="menu-item"><a href="/show_orders?page=${p}"> ${p} </a></li>
                            </c:forEach>
                        </c:when>

                        <c:otherwise>
                            <c:choose>
                                <c:when test="${ordersList.page < 3}">
                                    <c:forEach begin="1" end="5" var="p">
                                        <li class="menu-item"><a href="/show_orders?page=${p}"> ${p} </a></li>
                                    </c:forEach>
                                </c:when>
                                <c:when test="${ordersList.page > pageNumber - 3}">
                                    <c:forEach begin="${pageNumber - 4}" end="${pageNumber}" var="p">
                                        <li class="menu-item"><a href="/show_orders?page=${p}"> ${p} </a></li>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach begin="${ordersList.page - 1}" end="${ordersList.page + 3}" var="p">
                                        <li class="menu-item"><a href="/show_orders?page=${p}"> ${p} </a></li>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>

                    <c:if test="${!ordersList.lastPage}">
                        <li class="menu-item"><a href="/show_orders?page=${pageNumber}"><fmt:message key="last"/></a></li>
                    </c:if>
                </ul>

            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>
