package danny.socialmedia.jwts;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import danny.socialmedia.dtos.Response;
import danny.socialmedia.dtos.ResponseStatus;
import danny.socialmedia.dtos.TokenResponse;
import danny.socialmedia.dtos.UserDto;
import danny.socialmedia.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import static java.util.Date.from;

public class JwtAuthentication extends UsernamePasswordAuthenticationFilter {

    public static final String SECRET_KEY = "PAPADOPOULOS";

    private AuthenticationManager authenticationManager;
    private Logger logger = LoggerFactory.getLogger(getClass());





    public JwtAuthentication(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl("/users/auth/");
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {


        try {
            UserDto userDto = new ObjectMapper()
                    .readValue(req.getInputStream(), UserDto.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userDto.getUsername(),
                    userDto.getPassword(),new ArrayList<>()));

        } catch (IOException e) {
            logger.info(e.getMessage());

        }
        return null;

    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {



    User user = (User) auth.getPrincipal();
    Date issuedAt= from(Instant.now());
    Date expiresAt = from(issuedAt.toInstant().plusSeconds(900000));

    String token = JWT.create()
            .withSubject(user.getUsername())
            .withIssuedAt(issuedAt)
            .withExpiresAt(expiresAt)
            .withClaim("username",user.getUsername())
            .sign(Algorithm.HMAC512(SECRET_KEY.getBytes()));

        TokenResponse tokenResponse = TokenResponse.builder()
                .token(token)
                .expiresAt(expiresAt)
                .build();

        Response<?> res = Response.build(ResponseStatus.Successful,tokenResponse);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(res));
        response.getWriter().flush();

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

    Response<?> res = Response.build(ResponseStatus.NotSuccessful,"incorrect credentials");

    response.getWriter().write(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(res));
    response.getWriter().flush();



    }
}














