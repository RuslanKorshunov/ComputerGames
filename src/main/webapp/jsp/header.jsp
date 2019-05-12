<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html dir="ltr">
<fmt:setLocale value="en_US" scope="session" />
<fmt:setBundle basename="prop.text"  />
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="css/header_style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
</head>
<body>
    <div class="header">
        <h2 class="logo"><fmt:message key="label.games"/></h2>
        <input type="checkbox" id="chk">
        <label for="chk" class="show-menu-btn">
            <i class="fas fa-ellipsis-h"></i>
        </label>

        <ul class="menu">
            <a href="ControlServlet?command=get_user_info">${name} ${surname}</a>
            <a href="ControlServlet?command=get_games&page=0"><fmt:message key="menu.games"/></a>
            <a href="#"><fmt:message key="menu.developers"/></a>
            <a href="#"><fmt:message key="menu.ratings"/></a>
            <a href="ControlServlet?command=logout"><fmt:message key="label.logout"/></a>
            <label for="chk" class="hide-menu-btn">
                <i class="fas fa-times"></i>
            </label>
        </ul>
    </div>
</body>
</html>