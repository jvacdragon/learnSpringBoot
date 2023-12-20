package com.restapi.webservices.restfulwebservice.user;

import com.restapi.webservices.restfulwebservice.jpa.PostRepository;
import com.restapi.webservices.restfulwebservice.jpa.UserRepository;
import com.restapi.webservices.restfulwebservice.post.Post;
import com.restapi.webservices.restfulwebservice.post.PostForUserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaController {
    private UserRepository userRepository;
    private PostRepository postRepository;
    public UserJpaController(UserRepository userRepository, PostRepository postRepository){
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }
    @GetMapping(path = "/jpa/users")
    public List<User> retrieveUsers()
    {
        return userRepository.findAll();
    }
    @GetMapping("/jpa/users/{id}")
    public User retrieveUserById(@PathVariable long id){
        Optional<User> findedUser = userRepository.findById(id);

        if(findedUser.isEmpty()){
            throw new UserNotFoundException("id " + id + " não encontrada");
        }

        return findedUser.get();
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
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) throw new UserNotFoundException("Não é possível deletar usuário que não existe.");
        userRepository.deleteById(id);

        URI location = new URI("/users");
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable long id){
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("id " + id + " não encontrada");
        }

        return user.get().getPosts();
    }

    @GetMapping("/jpa/users/{id}/posts/{idPost}")
    public Post retrivePostForUser(@PathVariable long id, @PathVariable long idPost){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("Usuário não encontrado");
        }
        Optional<Post> post = user.get().getPosts().stream().filter(posts -> posts.getId().equals(idPost)).findFirst();
        if(post.isEmpty()){
            throw new PostForUserNotFoundException("Post não encontrado");
        }

        return post.get();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable long id, @Valid @RequestBody Post post){
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("id " + id + " não encontrada");
        }

        post.setUser(user.get());
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(post.getId())
                        .toUri();

        return ResponseEntity.created(location).build();
    }

    @Transactional
    @DeleteMapping("/jpa/users/{id}/posts/{idPost}")
    public void deletePostForUser(@PathVariable long id, @PathVariable long idPost){
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("id " + id + " não encontrada");
        }
        Optional<Post> post = user.get().getPosts().stream().filter(posts -> posts.getId().equals(idPost)).findFirst();

        if(post.isEmpty()){
            throw new UserNotFoundException("Post não encontrado");
        }

        postRepository.deleteByUserIdAndPostId(user.get().getId(), post.get().getId());
    }
}
