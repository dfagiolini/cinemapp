package me.fagiolini.cinemapp.exception;

public class myException extends Exception{
    public myException(String message){
        super(message);
    }
    public String getMessage(){
        return super.getMessage();
    }
}
