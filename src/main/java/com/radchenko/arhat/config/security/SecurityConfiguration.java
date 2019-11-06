package com.radchenko.arhat.config.security;

import com.radchenko.arhat.config.security.filter.jwt.JwtAuthenticationFilter;
import com.radchenko.arhat.config.security.filter.jwt.JwtAuthorizationFilter;
import com.radchenko.arhat.config.security.filter.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.radchenko.arhat.config.UrlMapping.LOGIN_ENDPOINT;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private BCryptPasswordEncoder passwordEncoder;
    private UserDetailsService userDetailService;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SecurityConfiguration(BCryptPasswordEncoder passwordEncoder, UserDetailsService userDetailService, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailService = userDetailService;
        this.jwtTokenProvider = jwtTokenProvider;
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
                        .antMatchers("/api/secure/useradmin").hasAnyRole("USER", "ADMIN")
                        .antMatchers("/api/secure/admin").hasAnyRole("ADMIN")
                        .antMatchers("/api/secure/authenticated").authenticated()
                    .and()
                        .addFilterAt(new JwtAuthenticationFilter(LOGIN_ENDPOINT, authenticationManager(), jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                        .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtTokenProvider))
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
