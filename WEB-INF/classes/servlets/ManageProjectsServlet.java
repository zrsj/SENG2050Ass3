package servlets;

import java.io.IOException;
import java.sql.*;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;

@WebServlet(urlPatterns = {"/ManageProjectsServlet"})
public class ManageProjectsServlet extends HttpServlet
{
    final private boolean DEBUG = true; 

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String username = request.getRemoteUser();
        HttpSession session = request.getSession();

        try
        {
            String countProjectsQuery = "SELECT COUNT(*) as count FROM tomcat_users_projects WHERE user_name = ?";
            ResultSet countResult = DBManager.processQuery(countProjectsQuery, username);
            countResult.next();
            final int numberOfProjects = countResult.getInt("count");
            
            ProjectBean[] projects = new ProjectBean[numberOfProjects];
            String retrieveProjectNamesQuery = "SELECT * FROM tomcat_users_projects WHERE user_name = ?";
            ResultSet retrieveNameResults = DBManager.processQuery(retrieveProjectNamesQuery, username);
            //ATTENTION: need to get project names and then query the other table for them.
            String[] projectNames = getProjectNames(request, response, retrieveNameResults, numberOfProjects);

            projects = retrieveProjects(request, response, projectNames, numberOfProjects);

            //projects = convertResultSetToArray(request, response, retrieveResults, numberOfProjects);
            session.setAttribute("projects", projects);

            redirect(request, response, "manageProjects.jsp");
        }
        catch(Exception e)
        {
            if(DEBUG)
            {
                System.out.println(1);
            }
            session.setAttribute("javaError", e.toString());
            redirect(request, response, "error.jsp");
        }
    }   

    private String[] getProjectNames(HttpServletRequest request, HttpServletResponse response, ResultSet result, int numberOfProjects)
    {
        String[] projectNames = new String[numberOfProjects];
        try{
            int count = 0;
            while(result.next())
            {
                projectNames[count] = result.getString("project_name");
                count++;
            }
        }
        catch(Exception e)
        {
            if(DEBUG)
            {
                System.out.println(2);
            }
            HttpSession session = request.getSession();
            session.setAttribute("javaError", e.toString());
            redirect(request, response, "error.jsp");
        }
        return projectNames;
    }

    private ProjectBean[] retrieveProjects(HttpServletRequest request, HttpServletResponse response, String[] projectNames, int numberOfProjects)
    {
        String name;
        String description;
        java.util.Date dateDue;
        java.sql.Date sqlDate;       
        String retrieveProjectDataQuery = "SELECT * FROM tomcat_projects WHERE project_name = ?";
        ProjectBean[] projects = new ProjectBean[numberOfProjects];
        ResultSet result;

        try
        {
            for(int i = 0; i < numberOfProjects; i++)
            {
                result = DBManager.processQuery(retrieveProjectDataQuery, projectNames[i]);
                result.next();
                name = result.getString("project_name");
                description = result.getString("project_description");
                sqlDate = result.getDate("date_due");
                dateDue = new java.util.Date(sqlDate.getTime());
                projects[i] = new ProjectBean(name, description, dateDue);
            }
        }
        catch(Exception e)
        {
            if(DEBUG)
            {
                System.out.println(3);
            }
            HttpSession session = request.getSession();
            session.setAttribute("javaError", e.toString());
            redirect(request, response, "error.jsp");           
        }
        return projects;
    }
    /*
    private ProjectBean[] convertResultSetToArray(HttpServletRequest request, HttpServletResponse response, ResultSet result, int numberOfProjects)
    {
        String name;
        String description;
        java.util.Date dateDue;
        java.sql.Date sqlDate;
        ProjectBean[] projects = new ProjectBean[numberOfProjects];
        try{
            int count = 0;
            while(result.next())
            {
                name = result.getString("project_name");
                description = result.getString("project_description");
                sqlDate = result.getDate("date_due");
                dateDue = new java.util.Date(sqlDate.getTime());
                projects[count] = new ProjectBean(name, description, dateDue);
                count++;
            }
        }
        catch(Exception e)
        {
            HttpSession session = request.getSession();
            session.setAttribute("javaError", e.toString());
            redirect(request, response, "error.jsp");
        }
        return projects;
    }*/

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
}