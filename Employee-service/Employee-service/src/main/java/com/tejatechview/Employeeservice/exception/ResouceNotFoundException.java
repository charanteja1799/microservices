package com.tejatechview.Employeeservice.exception;

public class ResouceNotFoundException {
    public String customException(String code){
        return "Not Found this Employee Details "+code;
    }
}
