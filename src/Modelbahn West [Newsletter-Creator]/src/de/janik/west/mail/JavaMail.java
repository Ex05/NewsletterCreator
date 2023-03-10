package de.janik.west.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class JavaMail
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private final String[]	recipients;

	private final String	msg;

	private final String	subject;

	private final Session	mailSession;

	// <- Static ->

	// <- Constructor ->
	public JavaMail(final String msg, final String subject, final String...recipients)
	{
		this.msg = msg;
		this.subject = subject;
		this.recipients = recipients;

		final Properties emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", "25");
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");
		mailSession = Session.getDefaultInstance(emailProperties, null);

	}

	// <- Abstract ->

	// <- Object ->
	public void sendMail()
	{
		try
		{
			final Transport transport = mailSession.getTransport("smtp");
			transport.connect("smtp.gmail.com", "user@mail", "*********");

			final MimeMessage emailMessage = new MimeMessage(mailSession);

			for (String recipient : recipients)
				emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

			emailMessage.setSubject(subject);
			emailMessage.setSender(new InternetAddress("user@mail"));
			emailMessage.setContent(msg, "text/html");

			transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
			transport.close();
		}
		catch (NoSuchProviderException e)
		{
			e.printStackTrace();
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
		}
	}
	// <- Getter & Setter ->
	// <- Static ->
}
