package com.exist.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exist.dao.UserDao;
import com.exist.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("User: " + username + "  not found");
		}
		return user;
	}
	
	/*@Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      	com.exist.model.User user = userDao.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        String[] authStrings = user.getAuthorities().split(", ");
        for(String authString : authStrings) {
            authorities.add(new SimpleGrantedAuthority(authString));
        }

        UserDetails ud = new User(user.getUsername(), user.getPassword(), authorities);
        return ud;
    }*/

}