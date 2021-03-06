package danny.socialmedia.services;


import danny.socialmedia.dtos.UserDto;
import danny.socialmedia.entities.Friend;
import danny.socialmedia.entities.User;
import danny.socialmedia.repositories.FriendRepository;
import danny.socialmedia.repositories.UserRepository;
import danny.socialmedia.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private Utils utils;

     private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        return user
                .orElseThrow(()-> new UsernameNotFoundException("USER not found"));
    }


    public User addUser(User user){
       // user.setRoles(user.getRoles());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String randomNumber = Utils.generateNum();
        user.setToken(randomNumber);
        System.out.println(randomNumber);
        System.out.println(user);
        return userRepository.save(user);


    }


    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public User getById(int id){
        return userRepository.findById(id). orElseThrow();

    }

    public String deleteById(int id){

         if(userRepository.findById(id).isPresent()){
             userRepository.deleteById(id);
         }else {
             return "User with id " + id + " is not found";
         }
        return "User with id " + id  + " has been deleted successfully ";
    }



    public User addFriend(int id,Friend friend){
       User user = userRepository.findById(id).get();
       friend.setUser(user);
       friendRepository.save(friend);
       return userRepository.save(user);

    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }


    public List<User> findByToken(String token){

        return userRepository.findByToken(token);
    }

    public User update(int id, UserDto userDto){
       User user1 = userRepository.findById(id).get();
       user1.setUsername(userDto.getUsername());
         return userRepository.save(user1);
    }


    public User getUsernameContaining(String username){
       return userRepository.findByUsernameContainingIgnoreCase(username).get();

    }

}
