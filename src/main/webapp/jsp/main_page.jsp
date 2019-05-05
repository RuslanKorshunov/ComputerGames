<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ctg" uri="CustomTags"%>
<html>
<fmt:setLocale value="en_US" scope="session" />
<fmt:setBundle basename="prop.text"  />
<head>
    <link rel="stylesheet" href="css/main_style.css">
    <title><fmt:message key="label.games"/></title>
</head>
<body>
    <c:import url="header.jsp"/>
    <c:choose >
        <c:when test="${fn:contains(list, 'games')}">
            <ctg:gamesTag pictures="${games}"/>
        </c:when>
        <c:when test="${fn:contains(list, 'developers')}">
            2
        </c:when>
        <c:when test="${fn:contains(list, 'rating')}">
            3
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>
</body>
</html>
