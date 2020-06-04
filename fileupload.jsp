<!DOCTYPE html>
<html>
    <head>
        <title>Upload Files</title>
    </head>
    <body>
        <h1>Upload a file:</h1>
        <form action="${pageContext.request.contextPath}/upload" name="fileupload" enctype="multipart/form-data" method="post">
            <input type="file" name="file" size="50" accept="*"/><br/>
            <input type="submit" value="Upload"/>
        </form>
    </body>
</html>
