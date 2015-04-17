<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<html>

<style>
    .error {
        color: black;
    }
</style>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<springForm:form commandName="userDTO" method="post" action="/user/add">
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

</body>
</html>
