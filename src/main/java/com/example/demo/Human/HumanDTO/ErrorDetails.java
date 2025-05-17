package com.example.demo.Human.HumanDTO;

import java.util.Date;

public class ErrorDetails {

    private String message;

    public ErrorDetails(String message, Date date, String webRequest) {
        this.message = message;
        this.date=date;
        this.webRequest=webRequest;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getWebRequest() {
        return webRequest;
    }

    private Date date;
    private String webRequest;




}
