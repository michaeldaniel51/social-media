package danny.socialmedia.repositories;


import danny.socialmedia.entities.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend,Integer> {




    List<Friend> findByUsername (String username);





}
