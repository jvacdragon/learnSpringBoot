package com.restapi.webservices.restfulwebservice.helloworld;

public class HelloWorldBean {
    private String message;
    public HelloWorldBean(String message){
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
