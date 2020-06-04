import java.io.*;

public class TestingClass{
    public static void main(String[] args){
        File file = new File("hello.c");
        // renaming the file and moving it to a new location 
        if(file.renameTo(new File("hello1.c"))){
            System.out.println("File copied successfully");
        }
        else{
            System.out.println("Failed to move the file");
        }
    }
}
