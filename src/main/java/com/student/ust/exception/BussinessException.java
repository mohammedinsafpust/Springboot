package com.student.ust.exception;

/**
 * The type Bussiness exception.
 */
public class BussinessException extends RuntimeException{
    /**
     * Instantiates a new Bussiness exception.
     *
     * @param exceptionMessage the exception message
     */
    public BussinessException(String exceptionMessage){
        super(exceptionMessage);
    }
}
