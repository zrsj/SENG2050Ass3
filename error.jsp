<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Error</title>
    </head>
    <body>
        <c:set var="error" value="${sessionScope.javaError}" />
        <h1>Error</h1>
        <p>Error:</p>
        <p><c:out value='${error}' /></p>
    </body>
</html>