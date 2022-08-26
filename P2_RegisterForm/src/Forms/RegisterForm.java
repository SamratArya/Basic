package Forms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//@WebServlet("/RegisterForm")
public class RegisterForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterForm() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/forms";
		String users = "root";
		String passes = "";
	    
		Connection con = null;
		PreparedStatement psmt = null;
		
		try
		{
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String username = request.getParameter("username");
			String password= request.getParameter("password");
			String conf_pass = request.getParameter("conf_pass");
			String dob = request.getParameter("dob");
			String gender = request.getParameter("gender");
			String stream = request.getParameter("stream");
			String hobbies = request.getParameter("hobbies");
			
			int pass = Integer.parseInt(password);
			int conf_p = Integer.parseInt(conf_pass);
			
			HttpSession ht = request.getSession();
			
			ht.setAttribute("first", firstname);
			ht.setAttribute("last", lastname);
			ht.setAttribute("user", username);
			ht.setAttribute("pass", password);
			ht.setAttribute("conf", conf_pass);
			ht.setAttribute("dob", dob);
			ht.setAttribute("gen", gender);
			ht.setAttribute("stream", stream);
			ht.setAttribute("hobb", hobbies);
			
			if(pass == conf_p)
			{
				RequestDispatcher rd = request.getRequestDispatcher("RegisterForm2");
				rd.forward(request, response);

			}
			else
			{
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Both passwords are differents');");
				out.println("</script>");
				response.sendRedirect("HeadPage.html");
			}
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, users, passes);
			System.out.println("Connection has been established");
		
			String sql = "insert into register_Form values(?,?,?,?,?,?,?,?,?)";

			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, firstname);
			psmt.setString(2, lastname);
			psmt.setString(3, username);
			psmt.setString(4, password);
			psmt.setString(5, conf_pass);
			psmt.setString(6, dob);
			psmt.setString(7, gender);
			psmt.setString(8, stream);
			psmt.setString(9, hobbies);
			
			int i = psmt.executeUpdate();
			System.out.println("Value has been updated : "+i);
			
			psmt.close();
			con.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
