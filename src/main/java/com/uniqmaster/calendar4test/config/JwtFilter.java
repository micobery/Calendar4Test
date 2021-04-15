package com.uniqmaster.calendar4test.config;

import com.uniqmaster.calendar4test.entity.UserEntity;
import com.uniqmaster.calendar4test.service.UserService;

import com.uniqmaster.calendar4test.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtFilter extends OncePerRequestFilter {
    private JwtUtils jwtUtils;
    private UserService userService;

    @Autowired
    public JwtFilter(JwtUtils jwtUtils, UserService usersService) {
        this.jwtUtils = jwtUtils;
        this.userService = usersService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String jwt = httpServletRequest.getHeader("Auth");

        if (jwt != null) {
            String username = jwtUtils.getUsername(jwt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserEntity user = (UserEntity) userService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


}
