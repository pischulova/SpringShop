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

        <spring:message code="sign_up" var="submitText"/>
        <springForm:form commandName="userDTO" method="post" action="/register_user">
        <table>
            <tr>
                <td><spring:message code="username"/> *</td>
                <td><springForm:input path="username" /></td>
                <td><springForm:errors path="username" cssClass="error" /></td>
            </tr>
            <tr>
                <td><spring:message code="pass"/> *</td>
                <td><springForm:input type="password" path="password" /></td>
                <td><springForm:errors path="password" cssClass="error" /></td>
            </tr>
            <tr>
                <td><spring:message code="confirm_password"/></td>
                <td><springForm:input type="password" path="confirmPassword" /></td>
                <td><springForm:errors path="confirmPassword" cssClass="error" /></td>
            </tr>
            <tr>
                <td><spring:message code="name"/> *</td>
                <td><springForm:input path="name" /></td>
                <td><springForm:errors path="name" cssClass="error" /></td>
            </tr>
            <tr>
                <td><spring:message code="email"/> *</td>
                <td><springForm:input path="email" /></td>
                <td><springForm:errors path="email" cssClass="error" /></td>
            </tr>
            <tr>
                <td><spring:message code="phone"/> *</td>
                <td><springForm:input path="phone" /></td>
                <td><springForm:errors path="phone" cssClass="error" /></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="${submitText}"></td>
            </tr>
        </table>
        </springForm:form>

<%@include file="/WEB-INF/layout/footer.jsp"%>