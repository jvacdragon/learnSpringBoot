package com.restapi.webservices.restfulwebservice.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> usersList = new ArrayList<>();

    static{
        usersList.add(new User(1, "Joao", LocalDate.now().minusYears(20).plusDays(6).minusMonths(
                4
        )));
        usersList.add(new User(2, "Nome 2", LocalDate.now().minusYears(30)));
        usersList.add(new User(3, "Nome 3", LocalDate.now().minusYears(25)));
    }

    public List<User> findAll(){
        return usersList;
    }
    public User save(User user){
        user.setId(4);
        user.setName("usuario 4");
        user.setBirthDate(LocalDate.now());
        usersList.add(user);
        return user;
    }

    public User findOne(long id){
        Predicate<User> predicate = user -> user.getId() == id;
        return usersList.stream().filter(predicate).findFirst().orElse(null);

    }
}
