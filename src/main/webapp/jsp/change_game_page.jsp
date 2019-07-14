<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="ctg" uri="CustomTags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <fmt:setLocale value="${lang}" scope="session"/>
    <fmt:setBundle basename="prop.text"  />
<head>
    <title><fmt:message key="label.change"/></title>
    <link rel="stylesheet" href="css/change_game_style.css">
</head>
<body>
    <c:import url="header.jsp"/>
    <div class="main">
        <form action="ControlServlet" method="post">
            <input class="txtb" type="text" name="game_name" value="${game_name}" placeholder="<fmt:message key="label.name"/>">
            <select class="txtb" name="genre">
                <option value="0" selected>
                </option>
                <option value="1">
                    <fmt:message key="genre.life_simulation"/>
                </option>
                <option value="2">
                    <fmt:message key="genre.interactive_movie"/>
                </option>
                <option value="3">
                    <fmt:message key="genre.rpg"/>
                </option>
            </select>
            <ctg:developerSelect developers="${developers}"/>
            <input class="txtb" type="text" name="year" value="${year}">
            <textarea class="review-text" placeholder="<fmt:message key="label.about"/>" name="about">${about}</textarea>
            <input class="btn" type="submit" value="<fmt:message key="label.save"/>">
            <input type="hidden" name="command" value="change_game">
            <input type="hidden" name="id" value="${id}">
        </form>
        <form action="ControlServlet" method="get">
            <input class="btn" type="submit" value="<fmt:message key="label.back"/>">
            <input type="hidden" name="command" value="get_game">
            <input type="hidden" name="id" value="${id}">
        </form>
    </div>>
</body>
</html>