<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница пользователя</title>
</head>
<body>
    <form action="ControlServlet" method="post">
        Имя: ${name} <input type="button" name="command" value="change_name"><br>
        Фамилия: ${surname}<br>
        Логин: ${login}<br>
        Пароль: ${password}<br>
        <c:choose>
            <c:when test="${role=1}">
                Статус: админ<br>
            </c:when>
            <c:when test="${role=1}">
                Статус: пользователь<br>
            </c:when>
            <c:otherwise>
                Статус: не определен<br>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${sex eq 'male'}">
                Пол: муж<br>
            </c:when>
            <c:when test="${sex eq 'female'}">
                Пол: жен<br>
            </c:when>
            <c:when test="${sex eq 'third'}">
                Пол: третий<br>
            </c:when>
            <c:otherwise>
                Пол: не определен
            </c:otherwise>
        </c:choose>
        Email: ${email}<br>
    </form>
</body>
</html>