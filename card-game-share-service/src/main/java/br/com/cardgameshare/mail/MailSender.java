package br.com.cardgameshare.mail;

import br.com.cardgameshare.dto.EmailDTO;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {

    private static String SMTP_PORT = "587";
    private static String SMTP_AUTH_INDICATOR = "true";
    private static String SMTP_START_TLS = "true";
    private static String SMTP_HOST = "smtp.gmail.com";

    private static String CGS_EMAIL = "cardgameshare@gmail.com";
    private static String CGS_PASSWORD = "slipKnot@1910";

    public static void enviarEmail(EmailDTO dto) throws Exception {

        try {
            // Step1
            Properties mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", SMTP_PORT);
            mailServerProperties.put("mail.smtp.auth", SMTP_AUTH_INDICATOR);
            mailServerProperties.put("mail.smtp.starttls.enable", SMTP_START_TLS);

            // Step2
            Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            MimeMessage generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(dto.getDestinatariosFormatadoEnvio()));
            generateMailMessage.setSubject(dto.getAssunto());
            generateMailMessage.setContent(dto.getCorpo(), "text/html");

            // Step3
            Transport transport = getMailSession.getTransport("smtp");
            transport.connect(SMTP_HOST, CGS_EMAIL, CGS_PASSWORD);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

}
