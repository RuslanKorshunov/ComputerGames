<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
    <c:set var="year_from" value="1900" scope="session"/>
    <c:set var="year_to" value="2050" scope="session"/>
    <c:set var="genre" value="0" scope="session"/>
    <c:set var="developer" value="0" scope="session"/>
    <c:set var="lang" value="en_US" scope="session"/>
    <jsp:forward page="jsp/main_page.jsp"/>
</body>
</html>