package com.restapi.webservices.restfulwebservice.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    UserDaoService userService;
    public UserResource(UserDaoService userService){
        this.userService = userService;
    }
    @GetMapping(path = "/users")
    public List<User> retrieveUsers(){
        return userService.findAll();
    }
    @GetMapping("/users/{id}")
    public User retrieveUserById(@PathVariable long id){
        User findedUser = userService.findOne(id);

        if(findedUser == null){
            throw new UserNotFoundException("id: " + id);
        }

        return findedUser;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@RequestBody User user){

        User savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
