package com.springboot.learnsspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//localhost:8080/courses
//[
// {
//    "id": value,
//        "name": value,
//        "author":> value
// }
// ]
@RestController //annotation for rest api
public class CurrencyConfigurationController {
    @Autowired
    private CurrencyServiceConfiguration configuration;
    @RequestMapping("/currency-configuration")
    public CurrencyServiceConfiguration retrieveAllCourses(){
        return configuration;
    }
}
