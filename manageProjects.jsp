<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Manage Projects</title>
    </head>
    <body>
        <h1>Manage Projects</h1>
        <form action="newProject.jsp"><button type="submit">Add new project</button></form>
        <table>
            <tr>
                <th>Project Name</th>
                <th>Project Description</th>
                <th>Date Due</th>
            </tr>
            <c:forEach var="project" items="${sessionScope.projects}">
                <tr>
                    <td>
                        <c:out value="${project.title}" />
                    </td>
                    <td>
                        <c:out value="${project.description}" />
                    </td>
                    <td>
                        <c:out value="${project.dateDue}" />
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>