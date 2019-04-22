<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Компьютерные игры</title>
</head>
<body>
    <c:import url="header.jsp"/>
    <form action="ControlServlet" method="get">
        <input type="hidden" name="command" value="logout">
        <input type="submit" name="button" value="Выйти">
    </form>
</body>
</html>
