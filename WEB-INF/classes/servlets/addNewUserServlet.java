package servlets;

import java.io.IOException;
import java.sql.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;


@WebServlet(urlPatterns = {"/addNewUserServlet"})
public class addNewUserServlet extends HttpServlet
{
    //Remove below
    final private boolean DEBUG = true;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("passwordConfirmation");
        String role = request.getParameter("role");

        removeErrorAttributes(request);

        try
        {
            if(checkForUsername(username))
            {
                usernameAlreadyTaken(request, response);
            }
        }
        catch(Exception e)
        {
            //Remove below
            if(DEBUG)
            {
                System.out.println(1);
            }
            HttpSession session = request.getSession();
            session.setAttribute("javaError", e.toString());
            redirect(request, response, "error.jsp");
        }

        if(!password.equals(passwordConfirmation))
        {
            passwordConfirmationFailed(request, response);
        }

        if(role == null)
        {
            roleNotSelected(request, response);
        }

        try
        {
            addUserToDB(username, password, role);

            redirect(request, response, "works.jsp");
        }
        catch(Exception e)
        {
            //Remove below
            if(DEBUG)
            {
                System.out.println("2");
            }
            HttpSession session = request.getSession();
            session.setAttribute("javaError", e.toString());
            redirect(request, response, "error.jsp");
        }

    }

    /**
     * Check if the username is already taken.
     * @param username The username that the database is being checked for.
     * @return true if the username is in the database, false if it isn't.
     */
    private boolean checkForUsername(String username)
        throws NamingException, SQLException
    {
        String query = "SELECT COUNT(*) as count FROM tomcat_users WHERE user_name = ?";

        ResultSet results = DBManager.processQuery(query, username);

        results.next();
        if(results.getInt("count") > 0)
        {
            return true;
        }
        return false;
        /*
        String query = "SELECT user_name FROM tomcat_users;";

        ResultSet results = DBManager.processQuery(query);
        while(results.next())
        {
            if(username.equals(results.getString("user_name")))
            {
                return true;
            }
        }
        return false;
        */
    }

    /**
     * Go back to signup.jsp and ask for a different username as the username entered has already been taken.
     * @param request The request that was passed into doPost.
     * @param response The response that was passed in doPost.
     */
    private void usernameAlreadyTaken(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        HttpSession session = request.getSession();
        session.setAttribute("usernameError", "usernameTaken");
        redirect(request, response, "signup.jsp");
    }

    /**
     * Inform the user their password and their password confirmation don't match.
     * @param request The request that was passed into doPost.
     * @param response The response that was passed in doPost.
     */
    private void passwordConfirmationFailed(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        HttpSession session = request.getSession();
        session.setAttribute("passwordError", "confirmationFailed");
        redirect(request, response, "signup.jsp");
    }

    /**
     * Inform the user they need to select a role.
     * @param request The request that was passed into doPost.
     * @param response The response that was passed in doPost.
     */
    private void roleNotSelected(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        HttpSession session = request.getSession();
        session.setAttribute("roleError", "roleNotSelected");
        redirect(request, response, "signup.jsp");
    }

    /**
     * Redirect to a jsp.
     * @param address The address of the jsp being redirected to.
     */
    private void redirect(HttpServletRequest request, HttpServletResponse response, String address)
    {
        try
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }

    /**
     * Remove any error attributes assigned to the session by this servlet.
     * @param request The request that was passed into doPost.
     */
    private void removeErrorAttributes(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.removeAttribute("usernameError");
        session.removeAttribute("passwordError");
    }

    private void addUserToDB(String username, String password, String role)
        throws NamingException, SQLException
    {
        String[] parameters = new String[2];

        parameters[0] = username;
        parameters[1] = password;
        String query = "INSERT INTO tomcat_users (user_name, password) VALUES (?, ?)";
        DBManager.processQueryWithoutResult(query, parameters);

        parameters[1] = role;
        query = "INSERT INTO tomcat_users_roles (user_name, role_name) VALUES (?, ?)";
        DBManager.processQueryWithoutResult(query, parameters);
    }
}