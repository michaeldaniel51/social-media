package danny.socialmedia.jwts;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import danny.socialmedia.entities.User;
import danny.socialmedia.repositories.UserRepository;
import danny.socialmedia.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthorization extends BasicAuthenticationFilter {




    public JwtAuthorization(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {


        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {

            chain.doFilter(request,response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request,response);

    }

        private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest req){

        try{
            String token = req.getHeader("Authorization");
            if(token != null){
                String user = JWT.require(
                        Algorithm.HMAC512(JwtAuthentication.SECRET_KEY.getBytes()))
                        .build()
                        .verify(token.replace("Bearer ","")).getSubject();
                if (user != null){
                    return new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>());
                }
        }

        }catch (IllegalStateException e){

            logger.error(e.getMessage());
        }
        return null;

        }

}
