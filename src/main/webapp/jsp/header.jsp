<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:out value="Добро пожаловать, "/>
    <a href="ControlServlet?command=get_user_info">${login}</a>!
    <hr/>
    <!--<form action="ControlServlet" method="get">
        <input type="hidden" name="command" value="search_games"/>
        <input type="submit" name="button" value="Игры">
    </form>-->
    <hr/>
</body>
</html>