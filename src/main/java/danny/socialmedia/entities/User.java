package danny.socialmedia.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class User implements UserDetails  {

    @Id
    @GeneratedValue
    private int id;

    @NotNull(message = "please enter username")
    private String username;

    @NotNull(message = "please enter password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;



    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String token ;



  //  private Roles roles = USER;


    @OneToOne
    @JsonIgnore
    private Friend friend;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;
    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
//        return Collections.singleton(new SimpleGrantedAuthority(roles.name()));
//
//    }

        @Override
        public boolean isAccountNonExpired () {
            return true;
        }

        @Override
        public boolean isAccountNonLocked () {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired () {
            return true;
        }

        @Override
        public boolean isEnabled () {
            return true;
        }
    }