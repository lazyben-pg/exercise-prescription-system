package com.lazyben.exercise.controller;

import com.lazyben.exercise.entity.User;
import com.lazyben.exercise.entity.UserResult;
import com.lazyben.exercise.service.AuthService;
import com.lazyben.exercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {
    private final UserService userService;
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserService userService,
                          AuthService authService,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/auth")
    public UserResult getIsLogin() {
        return authService.getCurrentUser()
                .map((loggedInUser) -> UserResult.success(null, loggedInUser))
                .orElse(UserResult.success(false, null));
    }

    @PostMapping("/auth/register")
    public UserResult register(@RequestBody Map<String, String> usernameAndPassword) {
        final String username = usernameAndPassword.get("username");
        final String password = usernameAndPassword.get("password");
        if (username == null || password == null) return UserResult.failure("用户名或密码为空");
        if (username.length() <= 1 || username.length() > 15) return UserResult.failure("用户名长度不合法");
        if (password.length() <= 6 || password.length() > 16) return UserResult.failure("密码长度不合法");
        final User user = userService.getUserByUsername(username);
        if (user != null) return UserResult.failure("改用户名已存在");
        userService.save(username, password);
        return UserResult.success("注册成功", userService.getUserByUsername(username));
    }

    @GetMapping("/auth/logout")
    public UserResult logout() {
        final UserResult userResult = authService.getCurrentUser()
                .map((loggedInUser) -> UserResult.success("注销成功"))
                .orElse(UserResult.failure("用户尚未登陆"));
        SecurityContextHolder.clearContext();
        return userResult;
    }

    @PostMapping("/auth/login")
    public UserResult login(@RequestBody Map<String, String> usernameAndPassword) {
        final String username = usernameAndPassword.get("username");
        final String password = usernameAndPassword.get("password");
        UserDetails userDetails;
        try {
            userDetails = userService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            return UserResult.failure("用户不存在");
        }
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            authenticationManager.authenticate(token);
            // cookie
            SecurityContextHolder.getContext().setAuthentication(token);
            return UserResult.success("登陆成功", userService.getUserByUsername(username));
        } catch (BadCredentialsException e) {
            return UserResult.failure("密码不正确");
        }
    }
}
