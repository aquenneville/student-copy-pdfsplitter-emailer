package org.quennea.service;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.quennea.domain.Student;
import org.quennea.domain.StudentCopy;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class PdfSplitterServiceImpl {

    private Properties props;

    public PdfSplitterServiceImpl() {
        props = new ApplicationConfigurationReaderImpl().read();
    }

    public void splitStudentCopies(List<Student> students, int classLevel) throws IOException { // String mainPdfDocumentFile) throws IOException {
        String mainPdfDocumentFile = "";
        mainPdfDocumentFile = props.getProperty("student.copy.class.level." + classLevel);

        if (mainPdfDocumentFile.length() > 0) {

            File file = new File(getClass().getClassLoader().getResource(mainPdfDocumentFile).getFile());
            PDDocument mainDocument = PDDocument.load(file);
            int pagePerStudent = mainDocument.getPages().getCount() / students.size();

            System.out.println("Found total pages in document: " + mainDocument.getPages().getCount());
            Splitter splitter = new Splitter();
            splitter.setSplitAtPage(pagePerStudent);
            List<PDDocument> splittedList = splitter.split(mainDocument);

            Iterator<Student> itStudent = students.iterator();
            for (PDDocument doc : splittedList) {
                Student currentStudent = null;
                if (itStudent.hasNext()) {
                    currentStudent = itStudent.next();
                }
                doc.save(new StudentCopy(currentStudent).getFilename());
                System.out.println("Splitted child document: " + new StudentCopy(currentStudent).getFilename());
                doc.close();
            }
            System.out.println("Document split completed");
            mainDocument.close();
        } else {
            System.out.println("Nothing to split classlevel not found");
        }
    }
}
