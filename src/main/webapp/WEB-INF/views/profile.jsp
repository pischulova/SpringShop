<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="/WEB-INF/layout/header.jsp"%>

<div class="body">
    <%@include file="/WEB-INF/layout/side_menu.jsp"%>
    <div class="content">
        <h1><fmt:message key="user_info"/></h1>

        <table>
            <tr>
                <td><fmt:message key="username"/></td>
                <td>${user.username}</td>
            </tr>
            <tr>
                <td><fmt:message key="name"/></td>
                <td>${user.name}</td>
            </tr>
            <tr>
                <td><fmt:message key="email"/></td>
                <td>${user.email}</td>
            </tr>
            <tr>
                <td><fmt:message key="phone"/></td>
                <td>${user.phone}</td>
            </tr>
        </table>

    </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>