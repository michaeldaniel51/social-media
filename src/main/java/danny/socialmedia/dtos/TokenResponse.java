package danny.socialmedia.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TokenResponse {

    private final String token;

    @JsonProperty(value = "expires_At")
    private final Date expiresAt;



}
