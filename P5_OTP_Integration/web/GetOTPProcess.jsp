<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Get OTP Process</title>
</head>
<body>
		
		<%
			String fullname = (String) session.getAttribute("fullname");
			String email = (String) session.getAttribute("email");
			String mobile = (String) session.getAttribute("mobile");
			int otp = (Integer) session.getAttribute("otp");
			
			String otpValue = request.getParameter("otpvalue");
			int enterOTP = Integer.parseInt(otpValue);
			
			if(enterOTP == otp)
			{
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amit", "root", "");
					Statement st = con.createStatement();
					
					String sql = "insert into mobileOTP values('"+fullname+"' , '"+email+"' , '"+mobile+"')";
					int result = st.executeUpdate(sql);
					
					out.println("Thank You for register "+result);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				out.println("OTP does not matched ");
			}
		%>
</body>
</html>