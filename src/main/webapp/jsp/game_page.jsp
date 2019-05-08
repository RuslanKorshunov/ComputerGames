<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="ctg" uri="CustomTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <fmt:setLocale value="en_US" scope="session" />
    <fmt:setBundle basename="prop.text"  />
<head>
    <title>${game_name}</title>
</head>
<body>
    <c:import url="header.jsp"/>
    ${game_name}<br>
    <c:choose>
        <c:when test="${fn:contains(genre, 'life_simulation')}">
            <fmt:message key="life_simulation"/>
        </c:when>
        <c:when test="${fn:contains(genre, 'interactive_movie')}">
            <fmt:message key="interactive_movie"/>
        </c:when>
        <c:otherwise>
            <fmt:message key="not_defined"/>
        </c:otherwise>
    </c:choose>
    ${developer}<br>
    <img src="${picture}"/><br>
</body>
</html>
