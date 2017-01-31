package hearingLevelTester;

import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mailer {
	public static boolean sendmail(String from, String password, String message, String to) {
		String host = "smtp.gmail.com";

		Properties prop = new Properties();
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.user", from);
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.password", password);
		prop.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(prop);

		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			mimeMessage.setFrom(new InternetAddress(from));
			
			InternetAddress address = new InternetAddress(to);
			mimeMessage.addRecipient(RecipientType.TO, address);
			
			mimeMessage.setSubject("#Your earTest Results:");
			mimeMessage.setText(message);
			
			Transport transport = session.getTransport("smtp");
			transport.connect(host,from,password);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			
			return true;
			
		} catch (Exception e) {
		}

		return false;
	}
}