<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Sign Up</title>
    </head>
    <body>
        <c:set var="usernameError" value="${sessionScope.usernameError}" />
        <c:set var="passwordError" value="${sessionScope.passwordError}" />
        <c:set var="roleError" value="${sessionScope.roleError}" />
        <h1>Sign Up</h1>
        <p>Welcome to ZIR!</p>
        <form action="addNewUserServlet" method="POST">
            <p>
                Please choose a user name:
                <br/>
                <input type="text" name="username" placeholder="username..."/>
            </p>
            <p>
                <c:if test = "${usernameError == 'usernameTaken'}">
                    Error: This username has already been taken.
                </c:if>
            </p>
            <p>
                Please choose a password:
                <br/>
                <input type="password" name="password" placeholder="password..."/>
            </p>
            <p>
                Please confirm your password:
                <br/>
                <input type="password" name="passwordConfirmation" placeholder="password..."/>
            </p>
            <p>
            <c:if test = "${passwordError == 'confirmationFailed'}">
                    Error: Passwords don't match.
            </c:if>
            </p>
            <p>
                Are you a member or manager of a project?
                <br/>
                <input type="radio" name="role" value="member" />
                <label for="member">Member</label>
                <br/>
                <input type="radio" name="role" value="manager">
                <label for="manager">Manager</label>
            </p>
            <p>
                <c:if test = "${roleError == 'roleNotSelected'}">
                        Error: No role has been selected.
                </c:if>
                </p>
            <input type="submit" value="Sign Up"/>
        </form>
    </body>
</html>