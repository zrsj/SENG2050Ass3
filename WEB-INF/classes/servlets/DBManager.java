package servlets;

import java.io.IOException;
import java.sql.*;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

public class DBManager 
{
    /**
     * Process a query that returns a result.
     * @param query The query that is going to be executed.
     * @param parameter The parameter for the prepared statement.
     * @return ResultSet containing the data retrieved by the query.
     */
    public static ResultSet processQuery(String query, String parameter)
        throws NamingException, SQLException
    {
        InitialContext context = new InitialContext();
        DataSource source = (DataSource) context.lookup("java:/comp/env/c3304621_assignment3/movieDB");
        Connection connection = source.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, parameter);
        ResultSet result = statement.executeQuery();
        return result;
    }

    /**
     * Process a query that won't return a result.
     * @param query The query that is going to be executed.
     * @param parameters The parameters for the prepared statement.
     */
    public static void processQueryWithoutResult(String query, String[] parameters)
        throws NamingException, SQLException
    {
        InitialContext context = new InitialContext();
        DataSource source = (DataSource) context.lookup("java:/comp/env/c3304621_assignment3/movieDB");
        Connection connection = source.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        for(int i = 0; i < parameters.length; i++)
        {
            statement.setString(i + 1, parameters[i]);
        }
        statement.execute();
    }

    /**
     * Add a project to the DB.
     * @param query The query that is going to be executed.
     * @param nameParameter The name of the project.
     * @param descriptionParameter The description of the project.
     * @param dateDueParameter The due date of the project.
     */
    public static void addProjectQuery(String nameParameter, String descriptionParameter, java.util.Date dateDueParameter)
        throws NamingException, SQLException
    {
        java.sql.Date sqlDateDue = new java.sql.Date(dateDueParameter.getTime());
        String query = "INSERT INTO tomcat_projects (project_name, project_description, date_due) VALUES (?, ?, ?)";
        InitialContext context = new InitialContext();
        DataSource source = (DataSource) context.lookup("java:/comp/env/c3304621_assignment3/movieDB");
        Connection connection = source.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, nameParameter);
        statement.setString(2, descriptionParameter);
        statement.setDate(3, sqlDateDue);
        statement.execute();
    }
}