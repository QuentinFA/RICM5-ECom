package emailManagement;

import java.util.Date;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Stateless
@LocalBean
public class EmailSessionBean {
	private int port = 587;
	private String host = "smtp.gmail.com";
	private String from = "ecomejb@gmail.com";
	private boolean auth = true;
	private String username = "ecomejb@gmail.com";
	private String password = "ecom2016";
	private Protocol protocol = Protocol.SMTPS;
	private boolean debug = true;
	
	
	public void sendMail(String to, String subject ,String body){
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		switch (protocol) {
		    case SMTPS:
		       // props.put("mail.smtp.ssl.enable", true);
		        props.put("mail.smtp.starttls.enable", "true");
		        break;
		    case TLS:
		        props.put("mail.smtp.starttls.enable", true);
		        break;
		}
		
		Authenticator authenticator = null;
		if (auth) {
		    props.put("mail.smtp.auth", true);
		    authenticator = new Authenticator() {
		        private PasswordAuthentication pa = new PasswordAuthentication(username, password);
		        @Override
		        public PasswordAuthentication getPasswordAuthentication() {
		            return pa;
		        }
		    };
		}
		
		Session session = Session.getInstance(props, authenticator);
		session.setDebug(debug);
		
		MimeMessage message = new MimeMessage(session);
		try {
		    message.setFrom(new InternetAddress(from));
		    InternetAddress[] address = {new InternetAddress(to)};
		    message.setRecipients(Message.RecipientType.TO, address);
		    message.setSubject(subject);
		    message.setSentDate(new Date());
		    //message.setText(body);
		    Multipart multipart = new MimeMultipart("alternative");
		    MimeBodyPart textPart = new MimeBodyPart();
		    String textContent = "Bienvenue sur UtByte!";
		    textPart.setText(textContent);
		    MimeBodyPart htmlPart = new MimeBodyPart();
		    String htmlContent = "<html><h1>Hi</h1><p>Vous vous êtes inscrit sur le meilleur site de ventre entre particulier au monde,Merci. <br/> On espère que vous allez faire de bonnes affaires grâce à ce site.</p></html>";
		    htmlPart.setContent(htmlContent, "text/html");
		    multipart.addBodyPart(textPart);
		    multipart.addBodyPart(htmlPart);
		    message.setContent(multipart);
		    Transport.send(message);
		} catch (MessagingException ex) {
		    ex.printStackTrace();
		}
	}
}
