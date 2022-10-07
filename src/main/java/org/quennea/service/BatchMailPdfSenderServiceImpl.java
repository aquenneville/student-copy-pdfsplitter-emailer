package org.quennea.service;

import org.quennea.domain.Student;
import org.quennea.domain.StudentCopy;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BatchMailPdfSenderServiceImpl {

    public void batchSendMail(List<Student> students) throws MessagingException, IOException {
        MailPdfSenderServiceImpl mailSender = new MailPdfSenderServiceImpl();
        MailSessionHandlerImpl sessionHandler = new MailSessionHandlerImpl();

        students.forEach(currentStudent -> {
            try {
                if (new File(new StudentCopy(currentStudent).getFilename()).exists()) {
                    mailSender.sendMail(sessionHandler.getSession(), currentStudent.getEmail(), new StudentCopy(currentStudent).getFilename());
                } else {
                    System.out.println("Skipping the email sending the file: " + new StudentCopy(currentStudent).getFilename() + ". File not found.");
                }
            } catch (MessagingException e) {
                System.out.println("Failed to send message: " + new StudentCopy(currentStudent).getFilename());
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
