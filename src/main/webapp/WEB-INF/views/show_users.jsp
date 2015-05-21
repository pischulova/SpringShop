<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="/WEB-INF/layout/header.jsp"%>

<div class="body">
    <%@include file="/WEB-INF/layout/side_menu.jsp"%>
    <div class="content">

        <c:choose>
            <c:when test="${listType =='clients'}">
                <c:set var="linkBase" value="admin/show_clients"/>
            </c:when>
            <c:when test="${listType =='blacklist'}">
                <c:set var="linkBase" value="admin/show_blacklist"/>
            </c:when>
            <c:when test="${listType =='admins'}">
                <c:set var="linkBase" value="admin/show_admins"/>
            </c:when>
        </c:choose>

        <ul class="users-menu">
            <li class="menu-item"><a href="/admin/show_clients"><spring:message code="clients"/></a></li>
            <li class="menu-item"><a href="/admin/show_blacklist"><spring:message code="blacklist"/></a></li>
            <li class="menu-item"><a href="/admin/show_admins"><spring:message code="admins"/></a></li>
        </ul> <br>

        <c:if test="${not empty usersList}">
            <table width="100%">
                <thead>
                <tr>
                    <th><fmt:message key="username"/></th>
                    <th><fmt:message key="name"/></th>
                    <th><fmt:message key="email"/></th>
                    <th><fmt:message key="phone"/></th>

                    <c:if test="${listType =='clients' || listType =='blacklist'}">
                        <th></th>
                    </c:if>
                </tr>
                </thead>

                <tbody class="striped">
                <c:forEach var="user" items="${usersList}">
                    <tr>
                        <td><c:out value="${user.username}" /></td>
                        <td><c:out value="${user.name}" /></td>
                        <td><c:out value="${user.email}" /></td>
                        <td><c:out value="${user.phone}" /></td>

                        <c:if test="${listType =='clients'}" >
                            <td>
                                <a href="<c:url value="/admin/add_to_blacklist/${user.id}"/>">
                                    <fmt:message key="to_blacklist"/>
                                </a>
                            </td>
                        </c:if>
                        <c:if test="${listType =='blacklist'}" >
                            <td>
                                <a href="<c:url value="/admin/unlock/${user.id}"/>">
                                    <fmt:message key="unlock"/>
                                </a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table><br>
        </c:if>

        <jsp:include page="/WEB-INF/layout/paging.jsp">
            <jsp:param name="link" value="${linkBase}?"/>
        </jsp:include>

    </div>
</div>

<%@include file="/WEB-INF/layout/footer.jsp"%>
