package danny.socialmedia.services;


import danny.socialmedia.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {




    public User authenticatedUser(){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return user;
    }










}
