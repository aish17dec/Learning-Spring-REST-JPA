package com.myrestapi.webservices.restfulwebservices.HelloWorld;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String helloWorld(){
        return "Hello World!, Let's learn how to build Spring RESTful apis";
    }

    @GetMapping(path= "/hello/{name}")
    public String helloName(@PathVariable String name){
        return "Hello "+name+"! Have an awsome day :)";
    }

    /*
    * @RequestMapping(method = RequestMethod.GET , path= "/hello/{name}")
    public String helloName(@PathVariable String name){
        return "Hello "+name+"! Have an awsome day :)";
    }
    * */

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean getHelloWorldBean(){
        return new HelloWorldBean("Aishwarya", "Spring");
    }
}
