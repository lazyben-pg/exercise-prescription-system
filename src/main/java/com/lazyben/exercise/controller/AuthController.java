package com.lazyben.exercise.controller;

import com.lazyben.exercise.entity.User;
import com.lazyben.exercise.entity.UserResult;
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
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/auth")
    public UserResult getIsLogin() {
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        final User loggedInUser = userService.getUserByUsername(username);
        if (loggedInUser == null) {
            return new UserResult(false, null, "ok", null);
        } else {
            return new UserResult(true, null, "ok", loggedInUser);
        }
    }

    @PostMapping("/auth/register")
    public UserResult register(@RequestBody Map<String, String> usernameAndPassword) {
        final String username = usernameAndPassword.get("username");
        final String password = usernameAndPassword.get("password");
        if (username == null || password == null) return new UserResult("fail", "用户名或密码为空");
        if (username.length() < 1 || username.length() > 15) return new UserResult("fail", "用户名长度不合法");
        if (password.length() < 6 || password.length() > 16) return new UserResult("fail", "密码长度不合法");
        final User user = userService.getUserByUsername(username);
        if (user != null) return new UserResult("fail", "改用户名存在");
        userService.save(username, password);
        return new UserResult(true, "注册成功", null, userService.getUserByUsername(username));
    }

    @GetMapping("/auth/logout")
    public UserResult logout() {
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        final User loggedInUser = userService.getUserByUsername(username);
        if (loggedInUser == null) {
            return new UserResult("fail", "用户尚未登陆");
        } else {
            SecurityContextHolder.clearContext();
            return new UserResult("ok", "注销成功");
        }
    }

    @PostMapping("/auth/login")
    public UserResult login(@RequestBody Map<String, String> usernameAndPassword) {
        final String username = usernameAndPassword.get("username");
        final String password = usernameAndPassword.get("password");
        UserDetails userDetails;
        try {
            userDetails = userService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            return new UserResult("fail", "用户不存在");
        }
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(token);
            return new UserResult(true, "登陆成功", "ok", userService.getUserByUsername(username));
        } catch (BadCredentialsException e) {
            return new UserResult("fail", "密码不正确");
        }
    }
}
