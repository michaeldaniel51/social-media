package danny.socialmedia.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import danny.socialmedia.dtos.Response;
import danny.socialmedia.dtos.UserDto;
import danny.socialmedia.entities.Friend;
import danny.socialmedia.entities.User;
import danny.socialmedia.services.UserService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;


@RestController
@RequestMapping("/users")
public class UserController {

    private final String PORT;
    private final ObjectMapper objectMapper;
    private final UserService userService;

    @Autowired
    public UserController(@Value("${server.port}") String PORT, ObjectMapper objectMapper, UserService userService) {
        this.PORT = PORT;
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> newUser(@RequestBody User user){
        return ok().body(userService.addUser(user));
    }

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        return ok().body(userService.findAllUser());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        return ok().body(userService.getById(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        return ok().body(userService.deleteById(id));
    }

    @PostMapping("/friend/{id}")
    public ResponseEntity<?> addFriend(@PathVariable int id, @RequestBody Friend friend){

        return ok().body(userService.addFriend(id,friend));


    }

    @PostMapping("/auth/")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDto userDto) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("http://localhost:" + PORT + "/users/auth/");
        httpPost.setEntity(new StringEntity(objectMapper.writeValueAsString(userDto)));

        CloseableHttpResponse response = httpClient.execute(httpPost);
        Response<?> responseEntity = objectMapper.readValue(response.getEntity().getContent(), Response.class);

        if (response.getStatusLine().getStatusCode() == 200){

            return ok(responseEntity);
        }else {
            return status(401)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseEntity);
        }
    }

    @GetMapping("/name/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username){

        return ResponseEntity.ok().body(userService.getUsernameContaining(username));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id,@RequestBody UserDto user){

        return ResponseEntity.ok().body(userService.update(id,user));
    }

}
