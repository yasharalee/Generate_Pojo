package com.cydeo.pojo_project;

public class NotValidPackageNameException extends RuntimeException{
    public NotValidPackageNameException(String message) {
            super(message);
    }
}
