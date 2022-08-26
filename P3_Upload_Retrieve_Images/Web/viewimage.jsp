<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
		<%
			Blob image = null;
			Connection con = null;
			byte[ ] imgData = null ;
			Statement stmt = null;
			ResultSet rs = null;
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
			    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/upload_images","root","");
			    stmt = con.createStatement();
			    
			    String sql = "select photo from uploadimage where contact_id = "+id+"";
			    
			    rs = stmt.executeQuery(sql);
			    
			    if(rs.next())
			    {
			    	image = rs.getBlob(1);
			    	imgData = image.getBytes(1 , (int)image.length());	
			    }
			    else
			    {
			    	out.println("Display Image not found");
			    	return;
			    }
			    
			    //display image
			    response.setContentType("image/jpg");
			    OutputStream o = response.getOutputStream();
			    o.write(imgData);
			    o.flush();
			    o.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		%>
</body>
</html>