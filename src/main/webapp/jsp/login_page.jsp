<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
<form action="ControlServlet" method="post">
    <input type="hidden" name="command" value="authorization">
    <table>
        <tr>
            <td>Логин: </td>
            <td><input type="text" name="login"></td>
        </tr>
        <tr>
            <td>Пароль: </td>
            <td><input type="text" name="password"></td>
        </tr>
        <tr>
            <td>
                <input type="submit" name="button" id="submit">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
