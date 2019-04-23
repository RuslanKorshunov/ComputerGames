<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>
        function changeDisplay(form) {
            if (form.style.display == "none") {
                form.style.display = "block";
            } else {
                form.style.display = "none";
            }
        }
    </script>
    <title>Страница пользователя</title>
</head>
<body>
    Имя: ${name} <input type="button" onclick="changeDisplay(document.getElementById('form_name'))"><br>
    <form id="form_name" action="ControlServlet" method="post" style="display: none;">
        <input type="text" name="new_name_form" value="${name}"> <input type="submit" value="Сохранить">
        <input type="hidden" name="command" value="change_name">
    </form>
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
</body>
</html>