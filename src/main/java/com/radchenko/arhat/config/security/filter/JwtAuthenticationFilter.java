package com.radchenko.arhat.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.radchenko.arhat.web.contoller.user.model.UserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(String url, AuthenticationManager authManager, JwtTokenProvider jwtTokenProvider) {
        super(new AntPathRequestMatcher(url, "POST"));
        setAuthenticationManager(authManager);
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserDto creds = new ObjectMapper().readValue(request.getInputStream(), UserDto.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication auth) {
        UserDetails user = (UserDetails) auth.getPrincipal();
        String role = user.getAuthorities().iterator().next().getAuthority();
        String token = jwtTokenProvider.generateToken(user.getUsername(), role);

        response.addHeader(jwtTokenProvider.getJwtHeaderString(), jwtTokenProvider.getJwtTokenPrefix() + token);
        response.addHeader("roles", role);
    }
}
