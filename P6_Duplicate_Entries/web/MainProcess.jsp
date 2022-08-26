<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Form</title>
</head>
<body>
	<%
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amit", "root", "");
			out.println("Connection has been established...");
			
			Statement st = null;
			PreparedStatement psmt = null;
			
			String checkQuery = "select count(*) from checkDuplicate where email = '"+email+"'";
			ResultSet rs = psmt.executeQuery();
			
			rs.next();
			
			String countRow = rs.getString(1);
			out.println(countRow);
			
			
			if(countRow.equals("0"))
			{
				//String sql = "insert into checkDuplicate (firstname,lastname,email,username,password) values(?,?,?,?,?)";
				String sql = "insert into checkDuplicate(firstname,lastname,email,username,password) values('"+firstname+"' , '"+lastname+"' , '"+email+"' , '"+username+"' , '"+password+"')";

				//psmt = con.prepareStatement(sql);
				//psmt.setString(1, firstname);
				//psmt.setString(2, lastname);
				//psmt.setString(3, email);
				//psmt.setString(4, username);
				//spsmt.setString(5, password);
				
				int res = st.executeUpdate(sql);
				out.println("Value has been inserted");	
			}
			else
			{
				out.println("Username or Email already exists");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	%>
</body>
</html>