/*
    Author: Zayne Jeffries - c3283006
    Purpose: Servlet to handle file downloading. This servlet is called when a
    user has requested a file to download from the download directory.
*/
import java.io.*;
import java.lang.Object;
import java.nio.file.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = {"/download"})
@MultipartConfig //required for the handling of files
public class DownloadServ extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException{
        String fileName = req.getParameter("filename");
        String filePath = req.getParameter("filepath");
        if(fileName == null){
            res.sendRedirect("filedownload.jsp");
        }
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        res.setContentType("APPLICATION/OCTET-STREAM"); //sets up servlet for downloading
        res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        FileInputStream fis = new FileInputStream(filePath + fileName);
        int i;
        while((i = fis.read()) != -1){
            out.write(i);
        }
        fis.close();
        out.close();
    }
}
