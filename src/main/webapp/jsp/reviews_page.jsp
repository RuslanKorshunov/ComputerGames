<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ct" uri="CustomTags"%>
<html>
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="prop.text"/>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/reviews_style.css">
    <link rel="stylesheet" href="css/main_style.css">
    <title><fmt:message key="label.reviews"/></title>
</head>
<body>
    <c:import url="header.jsp"/>
    <div class="reviews-main">
        <div class="navig">
            <form action="ControlServlet" method="get">
                <input type="submit" class="btn" value="<fmt:message key="label.backward"/>">
                <input type="hidden" name="page_number" value="${page_number}">
                <input type="hidden" name="id" value="${id}">
                <input type="hidden" name="command" value="backward_reviews">
            </form>
            <form action="ControlServlet" method="get">
                <input type="submit" class="btn" value="<fmt:message key="label.game"/>">
                <input type="hidden" name="command" value="get_game">
                <input type="hidden" name="id" value="${id}">
            </form>
            <form action="ControlServlet" method="get">
                <input type="submit" class="btn" value="<fmt:message key="label.forward"/>">
                <input type="hidden" name="page_number" value="${page_number}">
                <input type="hidden" name="id" value="${id}">
                <input type="hidden" name="command" value="forward_reviews">
            </form>
        </div>
        <ct:reviewTag reviews="${reviews}"/>
        <%--<div class="reviews">
            <div class="review">
            <h5>Ruslan Korshunov <fmt:message key="label.mark"/>: 5</h5>
            <textarea class="review-text" disabled></textarea>
            <c:if test="${role.getId()==1}">
                <form action="ControlServlet" method="get">
                    <input type="submit" class="btn" value="Delete">
                </form>
            </c:if>
        </div>
            <div class="review">
                <h5>Ruslan Korshunov <fmt:message key="label.mark"/>: 5</h5>
                <textarea class="review-text" disabled></textarea>
                <c:if test="${role.getId()==1}">
                    <form action="ControlServlet" method="get">
                        <input type="submit" class="btn" value="Delete">
                    </form>
                </c:if>
            </div>
        </div>--%>
    </div>
</body>
</html>
