package com.exist.service;

import com.exist.dao.UserDao;
import com.exist.dto.UserDto;
import com.exist.model.User;
import com.exist.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public void add(UserDto userDto) {
        UserRole userRole = UserRole.USER;

        if (userDto.getUserRoleDto().toString().equals("ADMIN")) {
            userRole = UserRole.ADMIN;
        }
        userDao.save(new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), userRole));
    }

    public boolean isExisting(String username) {
        User user = userDao.findByUsername(username);
        return user != null;
    }


}