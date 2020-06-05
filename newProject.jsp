<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Add New Project</title>
	<link rel="stylesheet" href="/c3304621_assignment3/css/style.css">
    </head>
    <body>
	<div class="hope">
		<div class="welcome">
        		<c:set var="nameError" value="${sessionScope.projectNameError}" />
        		<c:set var="descriptionError" value="${sessionScope.descriptionError}" />
        		<c:set var="dateDueError" value="${sessionScope.dateDueError}" />
        		<h1>Add New Project</h1>
		</div>
	</div>
	<div class="askLogin">
		<br><br>
        	<h2>Please enter the details of your project below.</h2>
		<br><br>
		<div class="User">
			<h3>
	
        			<form action="addNewProjectServlet" method="POST">
            			<p>
                			Please enter the name of the project:
                			<br/>
                			<input type="text" name="projectName" placeholder="project name..."/>
            			</p>
				<br>
            			<p>
                			<c:if test = "${nameError == 'projectNameTaken'}">
                    				Error: This project name is already in use.
                			</c:if>

                			<c:if test = "${nameError == 'nameNotEntered'}">
                    				Error: No project name entered.
                			</c:if>
           		 	</p>
				<br>
           		 	<p>
                			Please enter a description of the project:
                			<br/>
                			<input type="text" name="projectDescription" placeholder="type description here..."/>
           			 </p>
				<br>
            			 <p>
                			<c:if test = "${descriptionError == 'descriptionNotEntered'}">
                   	 			Error: No description entered.
               			         </c:if>
           			 </p>
				<br>
           		 	<p>
                			What date is your project due?
                			<br/>
                			<input type="date" name="projectDateDue" />
            			</p>
				<br>
            			<p>
                			<c:if test = "${dateDueError == 'dateNotEntered'}">
                        			Error: No date entered.
                			</c:if>
            			</p>
				<br>
           				 <input type="submit" value="Add Project"/>
        			</form>
			</h3>
		</div>
	</div>
    </body>
</html>