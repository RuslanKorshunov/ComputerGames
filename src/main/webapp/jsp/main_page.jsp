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
    <input type="hidden" name="page_number" value="${page_number}">
    <div class="gallery-section">
        <div class="inner-width">
            <h1>
                <c:choose >
                    <c:when test="${fn:contains(list, 'games')}">
                        <fmt:message key="menu.games"/>
                    </c:when>
                    <c:when test="${fn:contains(list, 'developers')}">
                        <fmt:message key="menu.developers"/>
                    </c:when>
                    <c:when test="${fn:contains(list, 'rating')}">
                        <fmt:message key="menu.ratings"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="label.main_page"/>
                    </c:otherwise>
                </c:choose>
            </h1>
            <div class="border"></div>
            <div class="gallery">
                <c:choose >
                    <c:when test="${fn:contains(list, 'games')}">
                        <ctg:pictureTag deliveries="${games}" pageNumber="${page_number}"/>
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
<%--                <form action="ControlServlet" method="get">
                    <input type="submit" name="command" value="forward">
                    <input type="submit" name="command" value="backward">
                    <input type="hidden" name="page_number" value="${page_number}">
                </form>--%>
            </div>
        </div>
    </div>
</body>
</html>
