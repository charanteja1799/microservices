package com.tejatechview.Departmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResourceNotFoundException extends RuntimeException{
    public ResponseEntity<Object> customException(String code){
        return new ResponseEntity<>("Not Found this Department "+code,HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> customException(Long code){
        return new ResponseEntity<>("Not Found this Department "+code,HttpStatus.NOT_FOUND);
    }
}
