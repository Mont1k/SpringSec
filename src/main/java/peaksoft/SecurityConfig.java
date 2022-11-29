package peaksoft;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.Authenticator;

@EnableWebMvc

public class SecurityConfig extends WebSecurityConfigurerAdapter {
@Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
    User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
    auth.inMemoryAuthentication()
            .withUser(userBuilder.username("Syimyk").password("syimik").roles("Student"))
            .withUser(userBuilder.username("Ulan").password("ulan").roles("Instructor","Manager"))
            .withUser(userBuilder.username("Esan").password("esan").roles("Manager"));
}
@Override
    protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/").hasAnyRole("Student","Instructor","Manager")
            .antMatchers("/instructor-info").hasRole("Instructor")
            .antMatchers("/manager-info").hasRole("Manager")
            .antMatchers("/studen-info").hasRole("Student");

}
}
