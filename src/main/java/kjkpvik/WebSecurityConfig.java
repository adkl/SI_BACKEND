package kjkpvik;

import kjkpvik.filters.JWTAuthenticationFilter;
import kjkpvik.filters.JWTLoginFilter;
import kjkpvik.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Adnan on 5/15/2017.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/korisnik/kreiraj").permitAll()
                .antMatchers("/korisnik/get").permitAll()
                .antMatchers("/korisnik/update").permitAll()
                .antMatchers("/korisnik/getByUsername").permitAll()
                .antMatchers("/zalbe/prikazi_zalbe").permitAll()
                .antMatchers("/zalbe/prikazi_javne_zalbe").permitAll()
                .antMatchers("/zalbe/prikazi_privatne_zalbe").permitAll()
                .antMatchers("/lokacija/all").permitAll()
                .antMatchers("/obavijest/sve").permitAll()
                .antMatchers("/anketa/aktivne").permitAll()
                .antMatchers("/anketa/prikazipitanja/**").permitAll()
                .antMatchers("/anketa/details/**").permitAll()
                .antMatchers("/obavijest/filtriraj/**").permitAll()
                .antMatchers("/contact/add").permitAll()
                .antMatchers("/contact/get").permitAll()
                .antMatchers("/zrijeci/prikazi_rijeci").permitAll()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .anyRequest().authenticated()
                .and()
                // We filter the api/login requests
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
