package com.springweb.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {

    @RequestMapping("say-hello")
    @ResponseBody
    public String hello(){
        return "Hello! What are you learning today?";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody //the body returns to the page directly the return in the webpage
    public String helloHtml(){
        return """
                <html>
                    <head>
                        <title>Site html java</title>
                    <head/>
                    <body>
                        <h1>Testando html</h1>
                        <p>ó o html aqui genteeee</p>
                    </body>
                </html>
                """;
    }

     //to use jsp:
    //src\main\resources\META-INF\resources\WEB-INF\jsp\sayHello.jsp
    //without th ResponseBody the MVC will search for the file using the suffix and prefix determined in the properties
     @RequestMapping("say-hello-jsp")
    public String sayHelloJsp(){
        return "sayHello";
    }
}
