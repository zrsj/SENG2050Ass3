<!DOCTYPE html>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<html>
    <head>
        <title>Archived Files</title>
    </head>
    <body>
        <h1>Download a file from the archives:</h1>
        <p>Files that are found here are the previous versions of uploaded files.
            If you are looking for the current version of these files, click <a href="filedownload.jsp">here</a>.</p>
        <jsp:include page="/archive" flush="true">
            <jsp:param name="path" value="${pageContext.request.contextPath}"/>
        </jsp:include>
    </body>
</html>
