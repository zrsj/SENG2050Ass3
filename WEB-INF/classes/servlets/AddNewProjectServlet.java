package servlets;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import javax.sql.rowset.serial.SerialException;


@WebServlet(urlPatterns = {"/addNewProjectServlet"})
public class AddNewProjectServlet extends HttpServlet
{  
    final private boolean DEBUG = true; 

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        removeErrorAttributes(request);

        String username = request.getRemoteUser();
        String projectName = request.getParameter("projectName");
        String projectDescription = request.getParameter("projectDescription");
        String dateParameter = request.getParameter("projectDateDue");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date projectDateDue = dateFormatter.parse(dateParameter);

            checkNullInputs(request, response, projectName, projectDescription, projectDateDue);
            checkProjectName(request, response, projectName);
            addProjectToDB(request, response, username, projectName, projectDescription, projectDateDue);
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
    }

    /**
     * Check if the user failed to enter any of the data for the project, mark error and redirect if they did.
     * @param request The request that was passed into doPost.
     * @param response The response that was passed in doPost.
     * @param projectName The name of the proejct.
     * @param projectDescription The description of the project.
     * @param projectDateDue The date the project is due.
     */
    private void checkNullInputs(HttpServletRequest request, HttpServletResponse response, String projectName, String projectDescription, Date projectDateDue)
        throws IOException, ServletException
    {
        try
        {
            if(projectName == null)
            {
                HttpSession session = request.getSession();
                session.setAttribute("projectNameError", "nameNotEntered");
                redirect(request, response, "newProject.jsp");
            }
            if(projectDescription == null)
            {
                HttpSession session = request.getSession();
                session.setAttribute("descriptionError", "descriptionNotEntered");
                redirect(request, response, "newProject.jsp");
            }
            if(projectDateDue == null)
            {
                HttpSession session = request.getSession();
                session.setAttribute("dateDueError", "dueDateNotEntered");
                redirect(request, response, "newProject.jsp");
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
    }

    /**
     * Check if the project name is already taken, mark error and redirect if it is.
     * @param request The request that was passed into doPost.
     * @param response The response that was passed in doPost.
     * @param projectName The name of the proejct.
     */
    private void checkProjectName(HttpServletRequest request, HttpServletResponse response, String projectName)
    {
        try
        {
            String query = "SELECT COUNT(*) as count FROM tomcat_projects WHERE project_name = ?";
            ResultSet result = DBManager.processQuery(query, projectName);
            result.next();

            if(result.getInt("count") == 1)
            {
                HttpSession session = request.getSession();
                session.setAttribute("nameError", "projectNameTaken");
                redirect(request, response, "signup.jsp");            
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
    }

    /**
     * Add the probject to the databse, redirects to the project management page.
     * @param request The request that was passed into doPost.
     * @param response The response that was passed in doPost.
     * @param username The name of the user creating the project.
     * @param projectName The name of the proejct.
     * @param projectDescription The description of the project.
     * @param projectDateDue The date the project is due.
     */
    private void addProjectToDB(HttpServletRequest request, HttpServletResponse response, String username, String projectName, String projectDescription, Date projectDateDue)
    {
        try
        {
            DBManager.addProjectQuery(projectName, projectDescription, projectDateDue);
            String query = "INSERT INTO tomcat_users_projects (user_name, project_name) VALUES (?, ?)";
            String[] parameters = new String[2];
            parameters[0] = username;
            //System.out.println(parameters[0]);
            parameters[1] = projectName;
            //System.out.println(parameters[1]);
            DBManager.processQueryWithoutResult(query, parameters);
            redirect(request, response, "/ManageProjectsServlet");
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
        session.removeAttribute("nameError");
        session.removeAttribute("descriptionError");
        session.removeAttribute("dateDueError");
    }
}