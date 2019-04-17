<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
    <form action="ControlServlet" method="post">
        <table>
            <tr>
                <td>Логин: </td>
                <td><input type="text" name="login"></td>
            </tr>
            <tr>
                <td>Пароль: </td>
                <td><input type="text" name="password"></td>
            </tr>
        </table>
        <input type="submit" name="button">
    </form>
</body>
</html>
