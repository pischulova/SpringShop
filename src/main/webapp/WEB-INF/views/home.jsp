<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
</head>
<body>

<form action="user/add" method="post">
    User name: <input type="text" name="name"></br>
    User login: <input type="text" name="price"></br>
    User password: <input type="text" name="pizzaType"></br>
    User phone: <input type="text" name="pizzaType"></br>
    User role:
    <select name="country">
        <option name="">-----</option>
        <option name="ADMIN">Admin</option>
        <option name="CLIENT">Client</option>
    </select>
    <input type="submit" value="Add">
</form>

</body>
</html>
