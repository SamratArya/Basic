package Mail;

import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter your Emailid");
		String email = in.next();
		
		System.out.println("Enter your Password");
		char[] pass = in.next().toCharArray();
		
		System.out.println("Enter the subject to post");
		String subject = in.next();
		
		System.out.println("Enter the content of the email body");
		String content = in.next();
		
		Properties prop = new Properties();
		try
		{
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.socketFactory.port", "465");
			prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.port", "465");
			
			Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator()
					{
						protected PasswordAuthentication getPasswordAuthentication()
						{
							return new PasswordAuthentication(email, pass);
						}
					});
			
			//try compose mail
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("divyasundarsahu@gmail.com"));//change accordingly
			message.addRecipient(Message.RecipientType.TO,new InternetAddress());
			message.setSubject(subject);
			message.setText(content);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
