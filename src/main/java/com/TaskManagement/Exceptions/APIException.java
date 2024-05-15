package com.TaskManagement.Exceptions;

public class APIException extends RuntimeException {
    private String messsage;
public APIException(String messsage){
    super(messsage);
    this.messsage = messsage;}
}
