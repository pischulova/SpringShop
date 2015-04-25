<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="/WEB-INF/layout/header.jsp"%>

<div class="body">
    <%@include file="/WEB-INF/layout/side_menu.jsp"%>
    <div class="content">


        <ul class="users-menu">
            <li class="menu-item"><a href="/show_clients"><spring:message code="clients"/></a></li>
            <li class="menu-item"><a href="/show_blacklist"><spring:message code="blacklist"/></a></li>
            <li class="menu-item"><a href="/show_admins"><spring:message code="admins"/></a></li>
        </ul> <br>

        <table width="100%">
            <thead>
            <tr>
                <th><fmt:message key="username"/></th>
                <th><fmt:message key="name"/></th>
                <th><fmt:message key="email"/></th>
                <th><fmt:message key="phone"/></th>

                <c:if test="${listType =='clients'}">
                    <th><fmt:message key="orders"/></th>
                    <th></th>
                </c:if>

            </tr>
            </thead>

            <tbody>
            <c:forEach var="user" items="${usersList.pageList}">
                <tr>
                    <td><c:out value="${user.username}" /></td>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.email}" /></td>
                    <td><c:out value="${user.phone}" /></td>

                    <c:if test="${listType =='clients' || listType =='blacklist'}" >
                        <td><c:out value="${user.orderAmount}" /></td>
                        <td>
                            <a href="<c:url value="add_to_blacklist/?id=${user.id}"/>">
                                <fmt:message key="to_blacklist"/>
                            </a>
                        </td>
                    </c:if>

                </tr>
            </c:forEach>
            </tbody>
        </table><br>



        <ul class="pages-menu">
            <c:if test="${!usersList.firstPage}">
                <li class="menu-item"><a href="/show_clients?page=${usersList.page - 1}"> Previous </a></li>
            </c:if>

            <%--<c:choose>--%>
                <%--<c:if test="${usersList.page < 3}">--%>

                <%--</c:if>--%>
            <%--</c:choose>--%>

            <c:forEach begin="${usersList.page + 1}" end="${usersList.page + 5}" var="p">
                <li class="menu-item"><a href="/show_clients?page=${p}"> ${p} </a></li>
            </c:forEach>

            <c:if test="${!usersList.lastPage}">
                <li class="menu-item"><a href="show_clients?page=${usersList.page + 1}"> Next </a></li>
            </c:if>
        </ul>    

    </div>
</div>

<%@include file="/WEB-INF/layout/footer.jsp"%>
