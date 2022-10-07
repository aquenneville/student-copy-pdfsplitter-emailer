package org.quennea;

import org.quennea.domain.Student;
import org.quennea.service.BatchMailPdfSenderServiceImpl;
import org.quennea.service.PdfSplitterServiceImpl;
import org.quennea.service.StudentReaderImpl;

import javax.mail.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


public class Application {

    public static void main(String[] args) throws IOException, MessagingException, URISyntaxException {
        boolean sendEmails = false;
        int classLevel = 4;
        List<Student> students = new StudentReaderImpl(classLevel).read(true);

        PdfSplitterServiceImpl splitterService = new PdfSplitterServiceImpl();
        splitterService.splitStudentCopies(students, classLevel);

        // mail student copy
        if (sendEmails) {
            BatchMailPdfSenderServiceImpl batchMailSender = new BatchMailPdfSenderServiceImpl();
            batchMailSender.batchSendMail(students);
        } else {
            System.out.println("Skipping email sending");
        }
    }
}
