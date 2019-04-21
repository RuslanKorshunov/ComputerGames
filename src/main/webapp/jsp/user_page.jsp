<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница пользователя</title>
</head>
<body>
    <c:import url="header.jsp"/>
    Имя: ${name}
    Фамилия: ${surname}
    Логин: ${login}
    Пароль: ${password}
    <c:choose>
        <c:when test="${role=1}">
            Роль: админ
        </c:when>
        <c:when test="${role=1}">
            Роль: пользователь
        </c:when>
    </c:choose>
    <c:choose>
        <c:when test="${sex eq 'male'}">
            Пол: муж
        </c:when>
        <c:when test="${sex eq 'female'}">
            Пол: жен
        </c:when>
        <c:when test="${sex eq 'third'}">
            Пол: третий
        </c:when>
        <c:otherwise>
            Пол:
        </c:otherwise>
    </c:choose>
    Email: ${email}
</body>
</html>
