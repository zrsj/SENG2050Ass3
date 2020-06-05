/*
    Author: Zayne Jeffries - c3283006
    Purpose: Servlet to handle file uploads from user.
    Takes any file type, saves to a directory called "uploadDirectory" and
    creates it if it does not exist.
*/
import java.io.*;
import java.lang.Object;
import java.nio.file.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = {"/downloadDirectory"})
@MultipartConfig //required for the handling of files
public class DownloadDirectoryServ extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException{
        PrintWriter out = res.getWriter();
        String str = req.getParameter("path") + "/../webapps/uploadDirectory/";
        File dlDir = new File("../webapps/uploadDirectory/");
        String[] files = dlDir.list();
        if(files == null)
            return;
        for(int i = 0; i < files.length; i++){
            if(files[i].compareTo("archive") == 0)
                continue;
            if(hasPreviousVersions(files[i])){
                out.println("<p>Note: the following file has archived versions. Click <a href=\"archive.jsp\">here<a/> to view archive.</p>");
            }
            out.println("<ul><a href=\"download?filename=" + files[i] + "&filepath=../webapps/uploadDirectory/\">" + files[i] + "</a></ul>");
        }
    }

    boolean hasPreviousVersions(String fl){
        File archiveDir = new File("../webapps/uploadDirectory/archive/");
        String[] files = archiveDir.list();
        if(files == null)
            return false;
        else{
            for(int i = 0; i < files.length; i++){
                if(files[i].contains(fl))
                    return true;
            }
        return false;
        }
    }
}
