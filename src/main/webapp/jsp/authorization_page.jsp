<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<head>
    <title><fmt:message key="label.log_in"/></title>
</head>
<body>
    <c:import url="header.jsp"/>
    <form action="ControlServlet" method="post">
        <input type="hidden" name="command" value="authorization">
        <table>
            <tr>
                <td><fmt:message key="label.login"/>: </td>
                <td><input type="text" name="login"/></td>
            </tr>
            <tr>
                <td><fmt:message key="label.password"/>: </td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" name="button" value="<fmt:message key="label.ok"/>"/>
                </td>
                <td>
                    <a href="ControlServlet?command=get_registration_page" >
                        <fmt:message key="label.registration"/>
                    </a>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>