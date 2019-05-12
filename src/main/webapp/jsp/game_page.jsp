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
            <h2><fmt:message key="label.genre"/>
                <div class="border"></div>
            </h2>
            <p>
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
            </p>
            <h2><fmt:message key="label.developer"/>
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
                Nothing now
            </p>
        </div>
        <form class="rating" action="ControlServlet" method="post">
            <input type="submit" name="star_form" id="star10" value="10">
            <label for="star10"></label>
            <input type="submit" name="star_form" id="star9" value="9">
            <label for="star9"></label>
            <input type="submit" name="star_form" id="star8" value="8">
            <label for="star8"></label>
            <input type="submit" name="star_form" id="star7" value="7">
            <label for="star7"></label>
            <input type="submit" name="star_form" id="star6" value="6">
            <label for="star6"></label>
            <input type="submit" name="star_form" id="star5" value="5">
            <label for="star5"></label>
            <input type="submit" name="star_form" id="star4" value="4">
            <label for="star4"></label>
            <input type="submit" name="star_form" id="star3" value="3">
            <label for="star3"></label>
            <input type="submit" name="star_form" id="star2" value="2">
            <label for="star2"></label>
            <input type="submit" name="star_form" id="star1" value="1">
            <label for="star1"></label>
            <input type="hidden" name="command" value="rate_game">
            <input type="hidden" name="id" value="${id}">
        </form>
    </div>
</body>
</html>
