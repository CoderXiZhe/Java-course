package com.bjpowernode.excep;

public class NotEnoughException extends  RuntimeException{
    public  NotEnoughException(){
        super();
    }
    public NotEnoughException(String message){
        super(message);
    }
}
