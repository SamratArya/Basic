package Forms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/RegisterForm2")
public class RegisterForm2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

    public RegisterForm2() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		HttpSession ht = request.getSession();

		PreparedStatement psmt = null;
		Connection con = null;
		
		try
		{
			String first = (String) ht.getAttribute("first");
			String last = (String) ht.getAttribute("last");
			String user = (String) ht.getAttribute("user");
			String pass = (String) ht.getAttribute("pass");
			String conf = (String) ht.getAttribute("conf");
			String dob = (String) ht.getAttribute("dob");
			String gen = (String) ht.getAttribute("gen");
			String stream = (String) ht.getAttribute("stream");
			String hobb = (String) ht.getAttribute("hobb");
			
			out.println("<html><head><body>");
			out.println("<h2>Firstname : "+first+"</h2>");
			out.println("<h2>Lastname : "+last+"</h2>");
			out.println("<h2>Username : "+user+"</h2>");
			out.println("<h2>Password : "+pass+"</h2>");
			out.println("<h2>Confirm_Password : "+conf+"</h2>");
			out.println("<h2>DOB : "+dob+"</h2>");
			out.println("<h2>Gender: "+gen+"</h2>");
			out.println("<h2>Stream : "+stream+"</h2>");
			out.println("<h2>Hobbies : "+hobb+"</h2>");
			out.println("</body></head></html>");
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doGet(request, response);
	}

}
