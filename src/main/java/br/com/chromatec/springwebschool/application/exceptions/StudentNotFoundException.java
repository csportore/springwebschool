package br.com.chromatec.springwebschool.application.exceptions;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
