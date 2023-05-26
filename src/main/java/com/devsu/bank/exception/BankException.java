package com.devsu.bank.exception;

public class BankException extends Exception{
    private String message;
    private Integer status;

    public BankException(String message, Integer status) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public  BankException(){
        super();
    }


    public Integer getStatus() {
        return status;
    }


    @Override
    public String getMessage() {
        return message;
    }
}
