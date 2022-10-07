package org.quennea.service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class MailSessionHandlerImpl {

    public Session getSession() {
        ApplicationConfigurationReaderImpl configReader = new ApplicationConfigurationReaderImpl();
        Properties props = configReader.read();

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("mail.username"), props.getProperty("mail.password"));
            }
        });
        return session;
    }
}
