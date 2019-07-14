<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="label.registration"/></title>
    <link rel="stylesheet" href="css/registration_style.css">
</head>
<body>
    <c:import url="header.jsp"/>
    <div class="main">
        <form action="ControlServlet" method="post">
        <table>
            <tr>
                <td>
                    <fmt:message key="label.login"/>
                </td>
                <td>
                    <input class="txtb" type="text" name="login">
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="label.password"/>
                </td>
                <td>
                    <input class="txtb" type="text" name="password">
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="label.email"/>
                </td>
                <td>
                    <input class="txtb" type="text" name="email">
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="label.name"/>
                </td>
                <td>
                    <input class="txtb" type="text" name="name">
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="label.surname"/>
                </td>
                <td>
                    <input class="txtb" type="text" name="surname">
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="label.sex"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="radio" id="radio1" name="sex" value="male">
                </td>
                <td>
                    <fmt:message key="label.male"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="radio" id="radio2" name="sex" value="female">
                </td>
                <td>
                    <fmt:message key="label.female"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="radio" id="radio3" name="sex" value="third">
                </td>
                <td>
                    <fmt:message key="label.third"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input class="btn" type="submit" name="button" value="<fmt:message key="label.registration"/>">
                    <input type="hidden" name="command" value="registration">
                </td>
            </tr>
        </table>
    </form>
    </div>
</body>
</html>