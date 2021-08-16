package danny.socialmedia.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Friend {


    @Id
    @GeneratedValue
    private int id;

    private String username;
    private int friendId;

    @OneToOne
    private User user;










}
