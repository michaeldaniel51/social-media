package danny.socialmedia.controllers;


import danny.socialmedia.entities.User;
import danny.socialmedia.services.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/friends")
public class FriendsController {

    private final FriendService friendService;


    @GetMapping("/{id}")
    public ResponseEntity<?> addFriend(@PathVariable int id) {

        return ResponseEntity.ok(friendService.aFriend(id));


    }


}










