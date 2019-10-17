package com.radchenko.arhat.config.security;

import com.radchenko.arhat.config.security.filter.AuthorizationFilter;
import com.radchenko.arhat.config.security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private BCryptPasswordEncoder passwordEncoder;
    private UserDetailsService userDetailService;

    @Autowired
    public SecurityConfiguration(BCryptPasswordEncoder passwordEncoder, UserDetailsService userDetailService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/*").permitAll()
                    .antMatchers("/h2_console/**").permitAll()
                    .and().authorizeRequests()
                        .antMatchers("/api/secure/useradmin").hasAnyRole("USER", "ADMIN")//FIXME doens't work
                        .antMatchers("/api/secure/admin").hasAnyRole("ADMIN")//FIXME doens't work
                        .antMatchers("/api/health/private").authenticated().and()
                .addFilterAt(new JwtAuthenticationFilter("/api/auth/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
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
        //do not create spring session
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin2").password(passwordEncoder.encode("email")).roles("USER", "ADMIN")
                .and()
                .withUser("user2").password(passwordEncoder.encode("email")).roles("USER");
    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
//    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/js/**", "/css/**", "/images/**", "/favicon.ico");
    }
}
