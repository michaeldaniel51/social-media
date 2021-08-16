package danny.socialmedia.services;


import danny.socialmedia.entities.Friend;
import danny.socialmedia.entities.User;
import danny.socialmedia.repositories.FriendRepository;
import danny.socialmedia.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        return user
                .orElseThrow(()-> new UsernameNotFoundException("USER not found"));
    }


    public User addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public User getById(int id){
        return userRepository.findById(id).orElseThrow();

    }

    public String deleteById(int id){
         userRepository.deleteById(id);
         return "User with id " + id  + " has been deleted successfully ";
    }

    public User addFriend(int id,Friend friend){

       User user = userRepository.findByFriendsId(id).get();

       friend.setUser(user);

        friendRepository.save(friend);
        return userRepository.save(user);

    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }



    public User getUsername(String username){

       return userRepository.findByUsernameContainingIgnoreCase(username).get();

    }


}
