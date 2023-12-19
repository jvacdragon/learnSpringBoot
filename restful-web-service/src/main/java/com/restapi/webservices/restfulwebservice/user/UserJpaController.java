package com.restapi.webservices.restfulwebservice.user;

import com.restapi.webservices.restfulwebservice.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserJpaController {
    UserRepository userRepository;
    public UserJpaController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @GetMapping(path = "/jpa/users")
    public List<User> retrieveUsers()
    {
        return userRepository.findAll();
    }
    @GetMapping("/jpa/users/{id}")
    public User retrieveUserById(@PathVariable long id){
        User findedUser = userRepository.findById(id);

        if(findedUser == null){
            throw new UserNotFoundException("id " + id + " não encontrada");
        }

        return findedUser;
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){

        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri(); //direcionar url para o objeto criado
        return ResponseEntity.created(location).build(); //responder com status de created (201)
    }

    @DeleteMapping(path="/jpa/users/{id}")
    public ResponseEntity deleteUser(@PathVariable long id) throws URISyntaxException {
        User user = userRepository.findById(id);
        if(user == null) throw new UserNotFoundException("Não é possível deletar usuário que não existe.");
        userRepository.deleteById(id);

        URI location = new URI("/users");
        return ResponseEntity.created(location).build();
    }
}
