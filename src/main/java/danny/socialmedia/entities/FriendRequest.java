package danny.socialmedia.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FriendRequest {



    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    private LocalDateTime timeSent = LocalDateTime.now();

    private LocalDateTime timeReceived = LocalDateTime.now();


}
