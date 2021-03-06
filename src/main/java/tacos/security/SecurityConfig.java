package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * @author keyboardhero
 * @create 2022-06-29 20:41
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication().withUser("buzz").password("infinity")
//                .authorities("ROLE_USER").and().withUser("woody").password("bullseye")
//                .authorities("ROLE_USER").and().passwordEncoder(new UserPasswordEncoder());
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/design","/orders").access("hasRole('ROLE_USER')")
                .antMatchers("/","/**").access("permitAll")
                .and().formLogin().loginPage("/login").and()
                .logout().logoutSuccessUrl("/").and().csrf().ignoringAntMatchers("/**")
                .and().headers().frameOptions().sameOrigin();
    }
    @Bean
    public PasswordEncoder encoder(){
        return new StandardPasswordEncoder("53cr3t");
    }

}
