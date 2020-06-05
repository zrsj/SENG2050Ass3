<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Error</title>
	<link rel="stylesheet" href="/c3304621_assignment3/css/style.css">
    </head>
    <body>
	<div class="hope">
		<div class="welcome">
        		<c:set var="error" value="${sessionScope.javaError}" />
        		<h1>Error</h1>
		</div>
	</div>
	<div class="askLogin">
		<br><br>
        	<h2>Sorry, there seems to be an error</h2>
        	<p><c:out value='${error}' /></p>
	</div>
    </body>
</html>