<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<fmt:setLocale value="en_US" scope="session" />
<fmt:setBundle basename="prop.text"  />
<head>
    <title><fmt:message key="label.games"/></title>
</head>
<body>
    <c:import url="header.jsp"/>
    <form action="ControlServlet" method="get">
        <input type="hidden" name="command" value="logout"/>
        <input type="submit" name="button" value="<fmt:message key="label.logout"/>"/>
    </form>
</body>
</html>
