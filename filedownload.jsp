<!DOCTYPE html>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<html>
    <head>
        <title>Download Files</title>
    </head>
    <body>
        <h1>Download a file:</h1>
        <jsp:include page="/downloadDirectory" flush="true">
            <jsp:param name="path" value="${pageContext.request.contextPath}"/>
        </jsp:include>
    </body>
</html>
