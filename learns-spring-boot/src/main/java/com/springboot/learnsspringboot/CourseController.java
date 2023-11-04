package com.springboot.learnsspringboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

//localhost:8080/courses
//[
// {
//    "id": value,
//        "name": value,
//        "author":> value
// }
// ]
@RestController //annotation for rest api
public class CourseController {
    @RequestMapping("/courses")
    public List<Course> retrieveAllCourses(){
        return Arrays.asList(
                new Course(1, "Curso 1", "Joao"),
                new Course(2, "Curso 2", "Vitor"),
                new Course(3, "Curso 3", "teste")
        );
    }
}
