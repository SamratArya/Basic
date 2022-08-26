package Upload;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/FileUpload")
@MultipartConfig(maxFileSize = 16177215)   //upload file upto 16mb
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String dbURL = "jdbc:mysql://localhost:3306/upload_images";
	private String dbUser = "root";
	private String dbPass = "";

    public FileUpload() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		
		InputStream inputStream = null; // input stream of the upload file
		
		// obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
         
        Connection conn = null; // connection to the database
        String message = null;  // message will be sent back to client
        
        try 
        {
            // connects to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUser , dbPass);
 
            // constructs SQL statement
            String sql = "INSERT INTO uploadimage (first_name, last_name, photo) values (?, ?, ?)";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, firstname);
            statement.setString(2, lastname);
             
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                statement.setBlob(3, inputStream);
            }
 
            // sends the statement to the database server
            int row = statement.executeUpdate();
            
            if (row > 0) {
                message = "File uploaded and saved into database";
            }
        }
        catch (Exception e) 
        {
			e.printStackTrace();
		}
        finally 
        {
            if (conn != null) 
            {
                // closes the database connection
                try 
                {
                    conn.close();
                } 
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }
            }
            // sets the message in request scope
            request.setAttribute("Message", message);
             
            // forwards to the message page
            getServletContext().getRequestDispatcher("/UploadMessage.jsp").forward(request, response);
        }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
