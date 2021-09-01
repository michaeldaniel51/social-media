package danny.socialmedia.services;


import danny.socialmedia.entities.Friend;
import danny.socialmedia.entities.User;
import danny.socialmedia.repositories.FriendRepository;
import danny.socialmedia.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    private final SecurityService securityService;


    public Friend aFriend(int id) {
         Friend friend = new Friend();
        User user = userRepository.findById(id).orElseThrow();

        if(user!=null){
            friend.setUser(user);
            friendRepository.save(friend);
        } else {
            throw new EntityNotFoundException();
        }

            return friend;
    }

}