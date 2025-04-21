package org.example.Exception;

public class LimitException extends RuntimeException{
    final int count;

    public LimitException(String message, int count){
        super(message);
        this.count = count;
    }

    public int getCount(){
        return count;
    }
}
