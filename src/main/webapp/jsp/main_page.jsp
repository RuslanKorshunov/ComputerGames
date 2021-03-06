<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ctg" uri="CustomTags"%>
<html>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="prop.text"  />
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/main_style.css">
    <title><fmt:message key="label.games"/></title>
</head>
<body>
    <c:import url="header.jsp"/>
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
                        <div class="navigation">
                            <form action="ControlServlet" method="get">
                                <input type="submit" class="btn" value="<fmt:message key="label.backward"/>">
                                <input type="hidden" name="command" value="backward_games">
                                <input type="hidden" name="page_number" value="${page_number}">
                            </form>
                            <form action="ControlServlet" method="get">
                                <input type="submit" class="btn" value="<fmt:message key="label.search"/>"/>
                                <input type="hidden" name="command" value="get_search_page"/>
                            </form>
                            <form action="ControlServlet" method="get">
                                <input type="submit" class="btn" value="<fmt:message key="label.forward"/>">
                                <input type="hidden" name="command" value="forward_games">
                                <input type="hidden" name="page_number" value="${page_number}">
                            </form>
                        </div>
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
            </div>
        </div>
    </div>
</body>
</html>