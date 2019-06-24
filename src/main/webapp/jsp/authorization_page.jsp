<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="label.log_in"/></title>
    <link rel="stylesheet" href="css/authorization_style.css">
</head>
<body>
    <c:import url="header.jsp"/>
    <div class="login-box">
        <div class="login-form">
            <h1><fmt:message key="label.welcome"/></h1>
            <form action="ControlServlet" method="post">
                <input class="txtb" type="text" name="login" placeholder="<fmt:message key="label.login"/>">
                <input class="txtb" type="password" name="password" placeholder="<fmt:message key="label.password"/>">
                <input class="btn" type="submit" name="button" value="<fmt:message key="label.ok"/>">
                <input type="hidden" name="command" value="authorization">
            </form>
            <form action="ControlServlet" method="get">
                <input class="btn" type="submit" value="<fmt:message key="label.registration"/>">
                <input type="hidden" name="command" value="get_registration_page">
            </form>
        </div>
    </div>
</body>
</html>