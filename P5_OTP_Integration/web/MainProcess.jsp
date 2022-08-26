<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*" %>
<%@page import="java.net.URL"%>                                                                     
<%@page import="java.net.URLConnection"%>                                                                                                                                                                                                                                                                                 
<%@page import=" java.net.URLEncoder"%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MAIN PROCESS</title>
</head>
<body>
		
		<%
			String fullname = request.getParameter("fullname");
			String email = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			
			//set the session
			session.setAttribute("fullname" , fullname);
			session.setAttribute("email", email);
			session.setAttribute("mobile", mobile);
			
			//Authenticatin key
			String authkey = "130491ADE9VcIY7WyB5vst82d3";
			
			//Multiples mobiles seperated by comma
			String mobiles = mobile;
			
			//generating otp and set the session
			Random random = new Random();
			int otp = random.nextInt(90000)+10000;
			session.setAttribute("otp", otp);
			
			//Sender ID,While using route4 sender id should be 6 characters long.
			String senderID = "ABCDEF";
			
			//Your message to send, Add url encoding here
			String message = "Your OTP is "+otp;
			
			//Define route
			String route = "4";
			
			//Prepare URL 
			URLConnection myurlConnect = null;
			URL myurl = null;
			BufferedReader reader = null;
			
			//encoding message
			String encodedMessage = URLEncoder.encode(message);
			
			//Send SMS API
			//String mainURL = "http://malussms.com/api/sendhttp.php?";
			//String mainURL = "http://api.msg91.com/api/sendhttp.php?";
			//String mainURL = "https://connect.routee.net/sms";
			//String mainURL = "https://api.textlocal.com/send/?";
			String mainURL = "https://rest.nexmo.com/sms/json";
			
			//Prepare parameters string
			StringBuilder strBld = new StringBuilder(mainURL);
			strBld.append("authKey="+authkey);
			strBld.append("&mobiles="+mobiles);
			strBld.append("&encodedMessage="+encodedMessage);
			strBld.append("&route="+route);
			strBld.append("&senderID="+senderID);
			
			//final string
			mainURL = strBld.toString();
			
			try
			{
				//Prepare Connection
				myurl = new URL(mainURL);
				myurlConnect = myurl.openConnection();
				myurlConnect.connect();
				
				reader = new BufferedReader(new InputStreamReader(myurlConnect.getInputStream()));
				
				String sucessMessage = "Your Message sent sucessfully ";
				response.sendRedirect("GetOTP.jsp");
				
				//close connection
				reader.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			
		%>
</body>
</html>