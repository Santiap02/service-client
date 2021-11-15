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
                //solo los miembros del rol admin podrán realizar altas
                //y para ver la lista de contactos, tendrán que estar autenticados
                .antMatchers(HttpMethod.POST,"/clientes").authenticated()
                .antMatchers(HttpMethod.POST,"/photos/add").authenticated()
                //.antMatchers("/**").authenticated()
                //.antMatchers("/contactos/**").authenticated()
                .and()
                .httpBasic();

    }
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
