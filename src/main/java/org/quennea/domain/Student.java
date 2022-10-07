package org.quennea.domain;

import lombok.Data;

@Data
public class Student {

    private String surname;
    private String firstName;
    private String email;
    private String className;

    public Student(String surname, String firstName, String email, String className) {
        this.surname = surname;
        this.firstName = firstName;
        this.email = email;
        this.className = className;
    }

}
