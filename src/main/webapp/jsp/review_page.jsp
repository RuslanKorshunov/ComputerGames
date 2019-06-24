<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ctg" uri="CustomTags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="prop.text"  />
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/review_style.css">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <title><fmt:message key="label.review"/></title>
</head>
<body>
    <c:import url="header.jsp"/>
    <div class="review-main">
        <div class="review-form">
            <h2><fmt:message key="label.mark"/>: ${mark}</h2>
            <form action="ControlServlet" method="post">
                <select name="mark">
                    <option value="0" selected>
                    </option>
                    <option value="1">
                        1
                    </option>
                    <option value="2">
                        2
                    </option>
                    <option value="3">
                        3
                    </option>
                    <option value="4">
                        4
                    </option>
                    <option value="5">
                        5
                    </option>
                    <option value="6">
                        6
                    </option>
                    <option value="7">
                        7
                    </option>
                    <option value="8">
                        8
                    </option>
                    <option value="9">
                        9
                    </option>
                    <option value="10">
                        10
                    </option>
                </select>
                <textarea class="review-text" name="comment">${comment}</textarea>
                <input type="submit" class="review-btn" value="<fmt:message key="label.save"/>">
                <input type="hidden" name="command" value="set_review">
                <input type="hidden" name="id" value="${id}">
            </form>
            <form action="ControlServlet" method="get">
                <input type="submit" class="review-btn" value="<fmt:message key="label.back"/>">
                <input type="hidden" name="command" value="get_game">
                <input type="hidden" name="id" value="${id}">
            </form>
        </div>
    </div>
</body>
</html>
