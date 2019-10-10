package com.radchenko.arhat.config.security;

import com.radchenko.arhat.config.security.filter.AuthenticationFilter;
import com.radchenko.arhat.config.security.filter.AuthorizationFilter;
import com.radchenko.arhat.config.security.filter.JwtAuthenticationFilter;
import com.radchenko.arhat.service.security.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private BCryptPasswordEncoder passwordEncoder;
    private UserDetailService userDetailService;

    @Autowired
    public SecurityConfiguration(BCryptPasswordEncoder passwordEncoder, UserDetailService userDetailService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/*").permitAll()
                    .antMatchers("/h2_console/**").permitAll()
                    .antMatchers("/api/health/private").authenticated().and()
                .addFilterAt(new JwtAuthenticationFilter("/api/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .authorizeRequests()
                .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logged-out")
                .permitAll();

        http.headers().frameOptions().disable();//make h2-console workable
        http.csrf().disable();
        //do not create spring session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/js/**", "/css/**", "/images/**", "/favicon.ico");
    }
}
