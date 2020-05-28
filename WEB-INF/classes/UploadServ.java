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

@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig //required for the handling of files
public class UploadServ extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException{
        Part filePart = req.getPart("file"); //retrieve the file
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream(); //prepare file for reading
        File uploadDir = new File("../webapps/uploadDirectory/"); //where files are to be stored
        if(!uploadDir.exists()) //if uploadDirectory does not exist then create it
            uploadDir.mkdir();
        System.out.printf("%s\n", uploadDir.getAbsolutePath()); //displays absolute filepath in terminal for checking
        File save = new File(uploadDir, fileName); //preps the save file to become a copy of the upload file
        Files.copy(fileContent, save.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}