<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ctg" uri="CustomTags"%>
<html>
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="prop.text"  />
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/search_style.css">
    <title><fmt:message key="label.search"/></title>
</head>
<body>
    <c:import url="header.jsp"/>
    <div class="main">
        <form action="ControlServlet" method="get">
            <table>
                <tr>
                    <td>
                        <h3><fmt:message key="label.year"/></h3>
                    </td>
                    <td>
                        <input class="txtb" type="text" name="year_from" placeholder="<fmt:message key="label.from"/>">
                    </td>
                    <td>
                        <input class="txtb" type="text" name="year_to" placeholder="<fmt:message key="label.to"/>">
                    </td>
                </tr>
                <tr>
                    <td>
                        <h3><fmt:message key="label.idGenre"/></h3>
                    </td>
                    <td>
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
                    </td>
                </tr>
                <tr>
                    <td>
                        <h3><fmt:message key="label.idDeveloper"/></h3>
                    </td>
                    <td>
                        <ctg:developerSelect developers="${developers}"/>
                    </td>
                </tr>
            </table>
            <input class="btn" type="submit" value="<fmt:message key="label.ok"/>">
            <input type="hidden" name="command" value="change_search_parameter">
            <input type="hidden" name="page_number" value="0">
        </form>
    </div>
</body>
</html>
