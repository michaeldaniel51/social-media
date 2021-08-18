package danny.socialmedia.securities;

import danny.socialmedia.jwts.JwtAuthentication;
import danny.socialmedia.jwts.JwtAuthorization;
import danny.socialmedia.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .disable()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/users/auth/","/users/**","/auth/","/email/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthentication(authenticationManager()))
                .addFilter(new JwtAuthorization(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().disable()
                .formLogin().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure (WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
                "/configuration/security", "/configuration/**", "/swagger-ui.html", "/webjars/**", "/swagger-ui/**", "/h2-console/**","/email/**","/users/**");
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){

        return new  BCryptPasswordEncoder();
    }



}
