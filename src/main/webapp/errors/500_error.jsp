<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>500 Page</title>
</head>
<body>
    Request from ${pageContext.errorData.requestURI} is failed  <br/>
    Servlet name: ${pageContext.errorData.servletName}  <br/>
    Status code: ${pageContext.errorData.statusCode}  <br/>
    Exception: ${pageContext.exception}  <br/>
    Message from exception: ${pageContext.exception.message}
</body>
</html>
