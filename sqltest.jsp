<%@ page import="java.util.List" %>
<%@ page import="javax.sql.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>SQL Test</title>
    </head>
    <body>
        <h1>SQL Test</h1>
        
        <%
        try
        {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/c3304621_assignment3/movieDB");

            Connection conn = ds.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM tomcat_users");

            while (rs.next()) { %>
                <p>
                    <%= rs.getString("user_name") %>
                </p>
            <%}
        }
        catch(Exception e)
        {
        %>
            <%= e.toString() %>
        <%}%>
        
    </body>
</html>