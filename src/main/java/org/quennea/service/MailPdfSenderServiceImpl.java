package org.quennea.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class MailPdfSenderServiceImpl {

    private Properties props;

    public MailPdfSenderServiceImpl() {
        props = new ApplicationConfigurationReaderImpl().read();
    }

    public void sendMail(Session session, String studentEmail, String filename) throws MessagingException, IOException {

        if (session != null) {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(props.getProperty("mail.from")));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(studentEmail));
            //InternetAddress.parse(props.getProperty("mail.from")));
            message.setSubject("Copie de ta dernière évaluation");

            String msg = "Bonjour, tu trouveras en pièce jointe la copie de ta dernière évaluation. <BR> N'hésite pas à me poser des questions si tu en as. <BR><BR> " + props.get("mail.signature");

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(new File(filename)); // check if file exists otherwise skip
            multipart.addBodyPart(attachmentBodyPart);

            Transport.send(message);
            try {
                Thread.sleep(Integer.valueOf(props.getProperty("mail.delay.ms")));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Send email to: " + studentEmail + " with attachment: " + filename);
        } else {
            System.out.println("Failed to get the mail session");
        }
    }
}
