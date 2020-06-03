package servlets;

import java.io.IOException;
import java.sql.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

public class DBManager 
{
    /**
     * Process a query.
     * Precondition: The string passed in is a valid query.
     * Postcondition: The returned ResultSet contains the data returned by the query.
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
}