<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="CustomTags"%>
<html>
<fmt:setLocale value="en_US" scope="session" />
<fmt:setBundle basename="prop.text"  />
<head>
    <title><fmt:message key="label.search"/></title>
</head>
<body>
    <c:import url="header.jsp"/>
    <form action="ControlServlet" method="get">
        <table>
            <tr>
                <td>
                    <fmt:message key="label.year"/>:
                </td>
                <td>
                    <fmt:message key="label.from"/>
                </td>
                <td>
                    <input type="text" name="year_from">
                </td>
                <td>
                    <fmt:message key="label.to"/>
                </td>
                <td>
                    <input type="text" name="year_to">
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="label.idGenre"/>
                </td>
                <td>
                    <select name="genre">
                        <option value="0" selected>
                        </option>
                        <option value="1">
                            <fmt:message key="idGenre.life_simulation"/>
                        </option>
                        <option value="2">
                            <fmt:message key="idGenre.interactive_movie"/>
                        </option>
                        <option value="3">
                            <fmt:message key="idGenre.rpg"/>
                        </option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="label.idDeveloper"/>
                </td>
                <td>
                    <ctg:developerSelect developers="${developers}"/>
                </td>
            </tr>
        </table>
        <input type="submit" name="command" value="change_search_parameter">
        <input type="hidden" name="page_number" value="0">
    </form>
</body>
</html>
