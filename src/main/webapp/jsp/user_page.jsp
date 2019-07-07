<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="prop.text"  />
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/user_style.css">
    <title><fmt:message key="label.user_page"/></title>
</head>
<body>
    <c:import url="header.jsp"/>
    <div class="main">
        <table>
            <tr>
                <td>
                    <fmt:message key="label.name"/>
                </td>
                <td>
                    <form id="form_name" action="ControlServlet" method="post">
                        <input class="txtb" type="text" name="new_name_form" value="${name}">
                        <input type="hidden" name="command" value="change_name">
                    </form>
                </td>
                <td>
                    <input form="form_name" class="btn" type="submit" value="<fmt:message key="label.change"/>">
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="label.surname"/>
                </td>
                <td>
                    <form id="form_surname" action="ControlServlet" method="post">
                        <input class="txtb" type="text" name="new_surname_form" value="${surname}">
                        <input type="hidden" name="command" value="change_surname">
                    </form>
                </td>
                <td>
                    <input form="form_surname" class="btn" type="submit" value="<fmt:message key="label.change"/>">
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="label.login"/>
                </td>
                <td>
                    ${login}
                </td>
            </tr>
            <tr>
               <td>
                   <fmt:message key="label.sex"/>
               </td>
               <td>
                   <c:choose>
                       <c:when test="${sex eq 'male'}">
                           <fmt:message key="label.male"/>
                       </c:when>
                       <c:when test="${sex eq 'female'}">
                           <fmt:message key="label.female"/>
                       </c:when>
                       <c:when test="${sex eq 'third'}">
                           <fmt:message key="label.third"/>
                       </c:when>
                       <c:otherwise>

                       </c:otherwise>
                   </c:choose>
               </td>
            </tr>
            <tr>
                <td>

                </td>
                <td>
                    <form id="form_sex" action="ControlServlet" method="post">
                        <input type="radio" id="radio1" name="new_sex_form" value="male">
                        <fmt:message key="label.male"/><br>
                        <input type="radio" id="radio2" name="new_sex_form" value="female">
                        <fmt:message key="label.female"/><br>
                        <input type="radio" id="radio3" name="new_sex_form" value="third">
                        <fmt:message key="label.third"/>
                        <input type="hidden" name="command" value="change_sex">
                    </form>
                </td>
                <td>
                    <input form="form_sex" class="btn" type="submit" value="<fmt:message key="label.change"/>">
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="label.email"/>
                </td>
                <td>
                    <form id="form_email" action="ControlServlet" method="post">
                        <input type="text" class="txtb" name="new_email_form" value="${email}">
                        <input type="hidden" name="command" value="change_email">
                    </form>
                </td>
                <td>
                    <input form="form_email" class="btn" type="submit" value="<fmt:message key="label.change"/>">
                </td>
            </tr>
        </table>
    </div>
</body>
</html>