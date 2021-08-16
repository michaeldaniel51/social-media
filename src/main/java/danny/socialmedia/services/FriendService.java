package danny.socialmedia.services;


import danny.socialmedia.entities.Friend;
import danny.socialmedia.entities.User;
import danny.socialmedia.repositories.FriendRepository;
import danny.socialmedia.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

//    public Friend addFriend(User id ,User userSender){
//        Friend friend = new Friend();
//        friend.setUser(userSender);
//        friend.setUsername(friend.getUsername());
////        friend.setUser(userSender);
//        friend.setFriendId();
//      return friendRepository.save(friend);



    }





