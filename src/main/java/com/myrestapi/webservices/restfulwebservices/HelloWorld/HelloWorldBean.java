package com.myrestapi.webservices.restfulwebservices.HelloWorld;

public class HelloWorldBean {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    private String subject;

    public HelloWorldBean(String name, String subject){
        this.name = name;
        this.subject = subject;
    }
}
