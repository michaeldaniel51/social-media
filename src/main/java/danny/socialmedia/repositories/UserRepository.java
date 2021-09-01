package danny.socialmedia.repositories;

import danny.socialmedia.entities.User;
import danny.socialmedia.utils.Utils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    Optional<User> findByUsername(String username);

 //   Optional<User> findByFriendsId(int id);

    Optional<User>findByUsernameContainingIgnoreCase(String username);

    List<User> findByToken(String token);
}
