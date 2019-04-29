<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<fmt:setLocale value="en_US" scope="session" />
<fmt:setBundle basename="prop.text"  />
<head>
    <title>Title</title>
</head>
<body>
    <fmt:message key="label.welcome"/>,
    <a href="ControlServlet?command=get_user_info">${name} ${surname}</a>!
    <hr/>
        <fmt:message key="menu.game"/>
        <fmt:message key="menu.developers"/>
        <fmt:message key="menu.ratings"/>
    <hr/>
</body>
</html>