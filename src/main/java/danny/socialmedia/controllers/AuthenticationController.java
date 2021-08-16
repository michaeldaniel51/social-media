package danny.socialmedia.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import danny.socialmedia.dtos.UserDto;
import danny.socialmedia.entities.User;
import org.apache.coyote.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

//@RestController
//@RequestMapping("/users/auth/")
public class AuthenticationController {

//
//    private final String PORT;
//    private final ObjectMapper objectMapper;
//

}
