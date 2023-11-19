package nl.novi.backendhuiswerktechiteasy.controllers;

import nl.novi.backendhuiswerktechiteasy.exceptions.NameNotApproved;
import nl.novi.backendhuiswerktechiteasy.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value= RecordNotFoundException.class)
    public ResponseEntity<Object> recordNotFound (RecordNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value= NameNotApproved.class)
    public ResponseEntity<Object> nameNotApproved (NameNotApproved exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }




}
