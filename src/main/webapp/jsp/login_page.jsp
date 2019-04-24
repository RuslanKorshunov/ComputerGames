<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<fmt:setLocale value="en_US" scope="session" />
<fmt:setBundle basename="prop.text"  />
<head>
    <title><fmt:message key="label.title"/></title>
</head>
<body>
<form action="ControlServlet" method="post">
    <input type="hidden" name="command" value="authorization">
    <table>
        <tr>
            <td><fmt:message key="label.login"/>: </td>
            <td><input type="text" name="login"/></td>
        </tr>
        <tr>
            <td><fmt:message key="label.password"/>: </td>
            <td><input type="text" name="password"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" name="button" value="<fmt:message key="label.ok"/>"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>