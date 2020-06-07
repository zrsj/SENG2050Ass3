/*
    Author: Zayne Jeffries - c3283006
    Purpose: Servlet to handle file downloading from the archive. This servlet
    is called when a user has requested a file to download from the archive 
    directory.
*/
import java.io.*;
import java.lang.Object;
import java.nio.file.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = {"/archive"})
@MultipartConfig //required for the handling of files
public class ArchiveServ extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException{
        PrintWriter out = res.getWriter();
        String str = req.getParameter("path") + "/../webapps/uploadDirectory/archive/";
        //out.println("<p>" + str + "</p>");
        File dlDir = new File("../webapps/uploadDirectory/archive/");
        String[] files = dlDir.list();
        if(files == null)
            return;
        out.println("<ul>");
        for(int i = 0; i < files.length; i++){
            out.println("\t<li><a href=\"download?filename=" + files[i] + "&filepath=../webapps/uploadDirectory/archive/\">" + files[i] + "</a></li>");
        }
        out.println("</ul>");
    }
}
