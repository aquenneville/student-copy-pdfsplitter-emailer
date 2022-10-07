package org.quennea.domain;

import lombok.Getter;

@Getter
public class StudentCopy {

    private String filename;

    public StudentCopy(Student student) {
        this.filename = "./"+student.getSurname()+"_"+student.getFirstName()+".pdf";
    }
}
