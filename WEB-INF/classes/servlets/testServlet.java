package servlets;

import java.io.IOException;
//import java.sql.*;

//import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
//import javax.sql.*;

@WebServlet(urlPatterns = {"/testServlet"})
public class testServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String username = request.getRemoteUser();
        System.out.println(username);
    }
}