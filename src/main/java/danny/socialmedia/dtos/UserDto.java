package danny.socialmedia.dtos;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserDto {


    private String username;

    private String password;

   // private Collection<GrantedAuthority> roles = new ArrayList<>();

}
