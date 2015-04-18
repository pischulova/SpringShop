<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>

<%@include file="/WEB-INF/layout/header.jsp"%>

<div class="body">
    <%@include file="/WEB-INF/layout/side_menu.jsp"%>
    <div class="content">
        <c:if test="${not empty message}">
            <p><spring:message code="${message}"/></p>
        </c:if>

        <springForm:form commandName="userDTO" method="post" action="/user/register">
        <table>
            <tr>
                <td>Username:</td>
                <td><springForm:input path="username" /></td>
                <td><springForm:errors path="username" cssClass="error" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><springForm:input type="password" path="password" /></td>
                <td><springForm:errors path="password" cssClass="error" /></td>
            </tr>
            <tr>
                <td>Confirm password:</td>
                <td><springForm:input type="password" path="confirmPassword" /></td>
                <td><springForm:errors path="confirmPassword" cssClass="error" /></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><springForm:input path="name" /></td>
                <td><springForm:errors path="name" cssClass="error" /></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><springForm:input path="email" /></td>
                <td><springForm:errors path="email" cssClass="error" /></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><springForm:input path="phone" /></td>
                <td><springForm:errors path="phone" cssClass="error" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="Register"></td>
            </tr>
        </table>
        </springForm:form>

<%@include file="/WEB-INF/layout/footer.jsp"%>