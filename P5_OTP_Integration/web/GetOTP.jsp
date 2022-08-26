<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Get OTP Input</title>
</head>
<body>
	<div class="cotainer">
		<form action="GetOTPProcess.jsp" method="get">
			<table>
				<tr>
					<td>Enter OTP : </td>
					<td>
						<input type="text" name="otpvalue" placeholder="Enter OTP">
					</td>
				</tr>
				
				<tr>
					<td></td>
					<td>
						<input type="submit" value="Submit">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>