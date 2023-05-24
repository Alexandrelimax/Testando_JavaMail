package enviando.mail;

import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ObjetoEnviaEmail {

	private String userName;
	private String password;
	private List<String> listaDestinatários;
	private String nomeRemetente;
	private String asuntoEmail;
	private String textoEmail;
	
	
	public ObjetoEnviaEmail(String userName, String password, List<String> listaDestinatários, String nomeRemetente,
			String asuntoEmail, String textoEmail) {
		
		this.userName = userName;
		this.password = password;
		this.listaDestinatários = listaDestinatários;
		this.nomeRemetente = nomeRemetente;
		this.asuntoEmail = asuntoEmail;
		this.textoEmail = textoEmail;
	}

	public void enviarEmail() throws Exception {

		Properties properties = new Properties();

		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true");// Autorização
		properties.put("mail.smtp.starttls", "true");// Autenticação
		properties.put("mail.smtp.host", "smtp.gmail.com");// Servidor gmail Google
		properties.put("mail.smtp.port", "465");// Porta do Servidor
		properties.put("mail.smtp.socketFactory.port", "465");// expecifica a porta a ser conectada pelo socket
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");// Classe socket de conexão ao
																							// smtp

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(userName, password);

			}

		});
		
		for (String destino : listaDestinatários) {
			
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, nomeRemetente));// Quem está enviando
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));// Email de Destino
		message.setSubject(asuntoEmail);// Assunto do e-mail
		message.setText(textoEmail);// Texto do e-mail

		Transport.send(message);
		}

	}

}
