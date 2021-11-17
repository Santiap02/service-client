package ApplicationConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final static String ADMIN = "ADMIN";
    private final static String CLIENTES = "/clientes";
    //definición roles y usuarios
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user1").password("{noop}user1")
                .roles("USER")
                .and()
                .withUser("admin")
                    .password("{noop}admin")
                    .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,CLIENTES).fullyAuthenticated()
                .antMatchers(HttpMethod.POST,"/photos/add").fullyAuthenticated()
                .antMatchers(HttpMethod.PUT,"/photos/update").fullyAuthenticated()
                .antMatchers(HttpMethod.PUT,"/clientes/actualizar/").fullyAuthenticated()
                .antMatchers(HttpMethod.DELETE,"/photos/").hasRole(ADMIN)
                .antMatchers(HttpMethod.DELETE,CLIENTES).hasRole(ADMIN)
                .antMatchers(HttpMethod.GET,"/clientes/*").fullyAuthenticated()
                .antMatchers(HttpMethod.GET,CLIENTES).fullyAuthenticated()
                .antMatchers(HttpMethod.GET, "/clientes/mayores/*").hasRole(ADMIN)
                .antMatchers(HttpMethod.GET, "/photo/*").fullyAuthenticated()
                .antMatchers(HttpMethod.GET, "/photos/*").fullyAuthenticated()
                .and()
                .httpBasic();

    }
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
