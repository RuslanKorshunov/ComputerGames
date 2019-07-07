<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="ctg" uri="CustomTags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <fmt:setLocale value="${lang}" scope="session"/>
    <fmt:setBundle basename="prop.text"  />
<head>
    <meta charset="utf-8">
    <title>${game_name}</title>
    <link rel="stylesheet" href="css/game_style.css">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
    <c:import url="header.jsp"/>
    <div class="card middle">
        <div class="top-section">
            <img src="${picture}" alt="">
        </div>

        <div class="info-section">
            <h2><fmt:message key="label.idGenre"/>
                <div class="border"></div>
            </h2>
            <p>
                <c:choose>
                    <c:when test="${fn:contains(genre, 'life_simulation')}">
                        <fmt:message key="genre.life_simulation"/>
                    </c:when>
                    <c:when test="${fn:contains(genre, 'interactive_movie')}">
                        <fmt:message key="genre.interactive_movie"/>
                    </c:when>
                    <c:when test="${fn:contains(genre, 'rpg')}">
                        <fmt:message key="genre.rpg"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="genre.unknown"/>
                    </c:otherwise>
                </c:choose>
            </p>
            <h2><fmt:message key="label.idDeveloper"/>
                <div class="border"></div>
            </h2>
            <p>
                ${developer}
            </p>
            <h2><fmt:message key="label.year"/>
                <div class="border"></div>
            </h2>
            <p>
                ${year}
            </p>
            <h2><fmt:message key="label.average_rating"/>
                <div class="border"></div>
            </h2>
            <p>
                ${rating}
            </p>
        </div>
        <div class="about-section">
            <h2><fmt:message key="label.about"/>
                <div class="border"></div>
            </h2>
            <p>
                ${about}
            </p>
        </div>
    </div>
    <div class="navigation">
        <c:if test="${role==1}">
            <form action="ControlServlet" method="get">
                <input type="submit" class="btn" value="<fmt:message key="label.change"/>">
                <input type="hidden" name="command" value="get_change_game_page">
                <input type="hidden" name="id" value="${id}">
            </form>
        </c:if>
        <c:if test="${role!=3}">
            <form action="ControlServlet" method="get">
                <input type="submit" class="btn" value="<fmt:message key="label.review"/>">
                <input type="hidden" name="command" value="get_review_page">
                <input type="hidden" name="id" value="${id}">
            </form>
        </c:if>
        <form action="ControlServlet" method="get">
            <input type="submit" class="btn" value="<fmt:message key="label.reviews"/>">
            <input type="hidden" name="command" value="get_reviews_page">
            <input type="hidden" name="id" value="${id}">
            <input type="hidden" name="page_number" value="0">
        </form>
    </div>
</body>
</html>
