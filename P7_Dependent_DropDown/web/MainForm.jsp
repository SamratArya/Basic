	<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	.s1
	{
		width: 150px;
	}
	
	#submit
	{
		margin-left: 40px;
		width: 70px;
	}


</style>
</head>
<body>
	<div class="container">
	<h2 align="center">Dependent DropDown List</h2>
		<form action="MainProcess.jsp" method="get">
			<table align="center" border="2px">
				<tr>
					<td>Country :</td>
					<td>
						<select class="s1" id="country" name="country" onchange="this.form.submit();">
							<option value="0">Select Country</option>
							
							<%
								try
								{
									Class.forName("com.mysql.cj.jdbc.Driver");
									Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amit", "root", "");
									Statement st = con.createStatement();
									
									String sql = "select * from country ";
									ResultSet rs = st.executeQuery(sql);
									
									while(rs.next())
									{
										%>
											<option value="<%= rs.getInt("country_id") %>"
											
												<%
													if(request.getParameter("country") != null)
													{
														if(rs.getInt("country_id") == Integer.parseInt(request.getParameter("country")))
														{
															out.println("selected");	
														}
													}
												%>
											>
														   <%= rs.getString("country_name") %>
											</option>										
										<% 
									}
								}
								catch(Exception e)
								{
									e.printStackTrace();	
								}
							%>
						</select>
					</td>
				</tr>
				
				<tr>
					<td>State :</td>
					<td>
						<select class="s1">
							<option value="0">Select State</option>
							
							<%
								try
								{
									Class.forName("com.mysql.cj.jdbc.Driver");
									Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amit", "root", "");
									
									PreparedStatement psmt = null;
									
									//String countryID = "select country_id from country";
									String sql = "select * from state where country_id = ?";
									
									psmt = con.prepareStatement(sql);
									psmt.setString(1, request.getParameter("country"));
									
									ResultSet rs = psmt.executeQuery();
									
									while(rs.next())
									{
										%>
											<option value="<%= rs.getInt("state_id") %>">
														   <%= rs.getString("state_name")%>
											</option>										
										<% 	
									}
								}
								catch(Exception e)
								{
									e.printStackTrace();	
								}
							%>
						</select>
					</td>
				</tr>
				
				<tr>
					<td></td>
					<td>
						<input type="submit" value="Submit" id="submit">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>