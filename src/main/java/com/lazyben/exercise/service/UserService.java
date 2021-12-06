package com.lazyben.exercise.service;

import com.lazyben.exercise.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserService implements UserDetailsService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
//        save("user", "123");
    }

    public com.lazyben.exercise.entity.User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    public void save(String username, String password) {
        userMapper.createUser(username, bCryptPasswordEncoder.encode(password));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.lazyben.exercise.entity.User user = userMapper.getUserByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username + " 不存在");
        return new User(username, user.getEncryptedPassword(), Collections.emptyList());
    }
}
