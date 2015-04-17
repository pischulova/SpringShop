<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<a href="/user">Register</a><br>
Language : <a href="?locale=en">English</a>|<a href="?locale=ru">Russian</a>

<h2>
    welcome.springmvc : <spring:message code="welcome_text" />
</h2>

Current Locale : ${pageContext.response.locale}
</body>
</html>
