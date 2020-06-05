<!DOCTYPE html>
<html>
    <head>
        <title>Upload Files</title>
        <script type="text/javascript" src="uploadFunction.js"></script>
    </head>
    <body>
        <h1>Upload a file:</h1>
        <form action="${pageContext.request.contextPath}/upload" onsubmit="uploadCheck()" name="fileupload" enctype="multipart/form-data" method="post">
            <input type="file" name="file" id="fileupload" size="50" accept="*"/><br/>
            <input type="submit" onclick="uploadCheck();" value="Upload"/>
        </form>
    </body>
</html>
