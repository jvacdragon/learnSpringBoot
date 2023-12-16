package com.restapi.webservices.restfulwebservice.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {
    UserDaoService userService;
    public UserController(UserDaoService userService){
        this.userService = userService;
    }
    @GetMapping(path = "/users")
    public List<User> retrieveUsers()
    {
        return userService.findAll();
    }
    @GetMapping("/users/{id}")
    public User retrieveUserById(@PathVariable long id){
        User findedUser = userService.findOne(id);

        if(findedUser == null){
            throw new UserNotFoundException("id " + id + " não encontrada");
        }

        return findedUser;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){

        User savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri(); //direcionar url para o objeto criado
        return ResponseEntity.created(location).build(); //responder com status de created (201)
    }

    @DeleteMapping(path="/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) throws URISyntaxException {
        User user = userService.findOne(id);
        if(user == null) throw new UserNotFoundException("Não é possível deletar usuário que não existe.");
        userService.deleteUser(id);

        URI location = new URI("/users");
        return ResponseEntity.created(location).build();
    }
}
