package com.springboot.learnjpaandhibernate.course;

import com.springboot.learnjpaandhibernate.course.jdbc.CourseJdbcRepository;
import com.springboot.learnjpaandhibernate.course.jpa.CourseJpaRepository;
import com.springboot.learnjpaandhibernate.course.springdatajpa.CourseSpringDataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {
    @Autowired
    private CourseJdbcRepository jdbcRepository;
    @Autowired
    private CourseJpaRepository jpaRepository;
    @Autowired
    private CourseSpringDataJpaRepository springJpaRepository;

    @Override
    public void run(String... args) throws Exception {
        Course coursesList[] = {
                new Course(1, "teste curso 1", "teste autor 1"),
                new Course(2, "teste curso 2", "teste autor 2"),
                new Course(3, "teste curso 3", "teste autor 3"),
        };
        for(Course course : coursesList){
            jdbcRepository.insert(course);
        }
        jdbcRepository.delete(coursesList[0].getId());

        jpaRepository.insert(new Course(4, "teste com jpa", "author com jpa"));
        jpaRepository.insert(new Course(5, "teste com jpa", "author com jpa"));
        jpaRepository.insert(new Course(6, "teste com jpa", "author com jpa"));

        jpaRepository.deleteById(2);
        jpaRepository.deleteById(5);

        jpaRepository.selectById(3);

        System.out.println(jpaRepository.selectById(3));
        System.out.println(jpaRepository.selectById(6));

        springJpaRepository.save(new Course(7, "teste com springJpa", "author com springJpa"));
        springJpaRepository.save(new Course(8, "teste com springJpa", "author com springJpa"));

        springJpaRepository.deleteById(7l);//7 long
        System.out.println(springJpaRepository.findById(8l));

        System.out.println(springJpaRepository.findByAuthor("teste autor 3"));

    }
}
