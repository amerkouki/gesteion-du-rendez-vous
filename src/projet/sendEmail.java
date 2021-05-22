package projet;

import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

public class sendEmail {

	public static void sendMail(String recept,String object,String msg) {
		System.out.println("debut ....... ");
		Properties properties=new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String myCompt="kouki.amer66@gmail.com";
		String password="AmerKoukiSem1997";
		
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new javax.mail.PasswordAuthentication(myCompt, password);
			}
		});
		
		try {
			Message message=perapreMessage(session,myCompt,recept,object,msg);
			Transport.send(message);
			System.out.println("message envoyer ");
		} catch (Exception e) {
			System.out.println("errrrrrrrrr envoyer ");
			e.printStackTrace();
		}
		
	}
	private static Message perapreMessage(Session session,String myCompt,String recpt,String object,String msg)  {
		Message message=new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myCompt));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recpt));
			message.setSubject(object);
			message.setText(msg);
			return message;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}
}
