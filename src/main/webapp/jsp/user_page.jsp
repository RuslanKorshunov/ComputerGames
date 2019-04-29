<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<fmt:setLocale value="en_US" scope="session" />
<fmt:setBundle basename="prop.text"  />
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
    <title><fmt:message key="label.user_page"/></title>
</head>
<body>
    <fmt:message key="label.name"/> : ${name} <input type="button" onclick="changeDisplay(document.getElementById('form_name'))"><br>
    <form id="form_name" action="ControlServlet" method="post" style="display: none;">
        <input type="text" name="new_name_form" value="${name}"> <input type="submit" value="<fmt:message key="label.save"/>">
        <input type="hidden" name="command" value="change_name">
    </form>
    <fmt:message key="label.surname"/> : ${surname} <input type="button" onclick="changeDisplay(document.getElementById('form_surname'))"><br>
    <form id="form_surname" action="ControlServlet" method="post" style="display: none;">
        <input type="text" name="new_surname_form" value="${surname}"> <input type="submit" value="<fmt:message key="label.save"/>">
        <input type="hidden" name="command" value="change_surname">
    </form>
    <fmt:message key="label.login"/> : ${login}<br>
    <fmt:message key="label.password"/> : ${password} <input type="button" onclick="changeDisplay(document.getElementById('form_password'))"><br>
    <form id="form_password" action="ControlServlet" method="post" style="display: none;">
        <input type="text" name="new_password_form" value="${password}"> <input type="submit" value="<fmt:message key="label.save"/>">
        <input type="hidden" name="command" value="change_password">
    </form>
    <fmt:message key="label.status"/>:
    <c:choose>
        <c:when test="${role eq 1}">
            <fmt:message key="label.administrator"/> <br>
        </c:when>
        <c:when test="${role eq 2}">
            <fmt:message key="label.user"/> <br>
        </c:when>
        <c:otherwise>
            <br>
        </c:otherwise>
    </c:choose>
    <fmt:message key="label.sex"/>:
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
    </c:choose> <input type="button" onclick="changeDisplay(document.getElementById('form_sex'))"><br>
    <form id="form_sex" action="ControlServlet" method="post" style="display: none">
        <input type="radio" id="radio1" name="new_sex_form" value="male">
        <fmt:message key="label.male"/>
        <input type="radio" id="radio2" name="new_sex_form" value="female">
        <fmt:message key="label.female"/>
        <input type="radio" id="radio3" name="new_sex_form" value="third">
        <fmt:message key="label.third"/>
        <input type="submit" value=" <fmt:message key="label.save"/>">
        <input type="hidden" name="command" value="change_sex">
    </form>
    <fmt:message key="label.email"/> : ${email} <input type="button" onclick="changeDisplay(document.getElementById('form_email'))"><br>
    <form id="form_email" action="ControlServlet" method="post" style="display: none;">
        <input type="text" name="new_email_form" value="${email}"> <input type="submit" value="<fmt:message key="label.save"/>">
        <input type="hidden" name="command" value="change_email">
    </form>
    <form action="ControlServlet" method="get">
        <input type="submit" name="button" value="<fmt:message key="label.go_back"/>">
        <input type="hidden" name="command" value="go_back">
    </form>
</body>
</html>