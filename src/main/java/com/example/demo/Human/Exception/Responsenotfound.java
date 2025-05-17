package com.example.demo.Human.Exception;

public class Responsenotfound extends RuntimeException{

    public String message;


    public Responsenotfound(String message) {
        super(message);
        this.message = message;
    }
}
