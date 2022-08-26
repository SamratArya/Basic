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
<title>View the Image Here</title>
</head>
<body>
	
	<%
	
		int id = Integer.parseInt(request.getParameter("id"));
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/upload_images", "root", "");
			
			String sql = "select filename from image_file where image_id "+id+"";
			PreparedStatement psmt = con.prepareStatement(sql);	
			
			ResultSet rs = psmt.executeQuery();	
			while(rs.next())
			{
				String filename = rs.getString("filename");	
			
	%>
	
	<table>
		<tr>
			<th>Image ID</th>
			<th>Image : </th>
		</tr>
		
		<tr>
			<td>
				<%= id %>
			</td>
			<td>
				<image src="Images/<%= filename %>" width="200" height="200"/>
			</td>
		</tr>
	</table>
	
	<%
	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	%>
	
	
</body>
</html>