package org.quennea.service;

import org.quennea.domain.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class StudentReaderImpl {

    private int classLevel;
    private Properties props;

    public StudentReaderImpl(int classLevel) {
        this.classLevel = classLevel;
        props = new ApplicationConfigurationReaderImpl().read();
    }

    public List<Student> read(boolean reverseOrder) throws IOException, URISyntaxException {
        String classFilename = props.getProperty("class.file.year."+classLevel);
        List<String> result = new ArrayList<>();
        if (classFilename.length() > 0) {
            File studentsFile = new File(getClass().getClassLoader().getResource(classFilename).getFile());
            boolean firstLine = true;
            try (BufferedReader br = new BufferedReader(new FileReader(studentsFile))) {
                while (br.ready()) {
                    String fileLine = br.readLine();
                    if (firstLine) {
                        if (fileLine.contains("@")) {
                            result.add(fileLine);
                        } else {
                            firstLine = false;
                        }
                    } else if (!firstLine) {
                        result.add(fileLine);
                    } else {
                        firstLine = false;
                    }
                }
            }
        }

        List<Student> students = result.stream()
                .map(t -> {
                    String studentVals[] = t.split(",");
                    return new Student(studentVals[0], studentVals[1], studentVals[2], studentVals[3]);
                })
                .collect(Collectors.toList());

        if (reverseOrder) {
            Collections.reverse(students);
        }
        return students;
    }
}
