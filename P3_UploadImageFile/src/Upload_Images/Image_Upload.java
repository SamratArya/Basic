package Upload_Images;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
//import com.oreilly.servlet.MultipartRequest;


//@WebServlet("/Image_Upload")

@MultipartConfig(fileSizeThreshold = 1024 * 1024 *2 , //2MB
maxFileSize = 1024 * 1024 * 10 , //10MB
maxRequestSize = 1024 * 1024 * 50) 

public class Image_Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Image_Upload() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String fullname = request.getParameter("fullname");
		Part part = request.getPart("file");
		String filename = extractFileName(part);
		String savePath = "home/user/eclipse-workspace/P3_UploadImageFile/src/Images/"+File.separator+filename;
		File filesaveDir = new File(savePath);
		
		part.write(savePath+File.separator);
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/upload_images", "root", "");
			
			String sql = "insert into image_file values(?,?,?,?)";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.setString(2, fullname);
			psmt.setString(3, filename);
			psmt.setString(4, savePath);
			
			int i = psmt.executeUpdate();
			System.out.println("Images has been uploaded "+i);
			
			out.println("<center><h1>IMAGE HAS BEEN INSERTED.....</h1></center>");
			out.println("<center><a href='view.jsp? id = "+id+"'></a></center>");
			
			psmt.close();
			con.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public String extractFileName(Part part)
	{
		String contentDisp = part.getHeader("content-disposition");
		
		String item[] = contentDisp.split(";");
		for(String s1:item)
		{
			if(s1.trim().startsWith("filename"))
			{
				return s1.substring(s1.indexOf("=")+2, s1.length()-1);
			}
		}
		return "";
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
