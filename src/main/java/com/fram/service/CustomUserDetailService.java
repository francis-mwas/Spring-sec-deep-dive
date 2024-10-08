package com.fram.service;

import com.fram.models.CurrentUserDetails;
import com.fram.models.Users;
import com.fram.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user =  userRepository.findByUsername(username);
        if(user == null){
            log.info("User Not Found");
            throw new UsernameNotFoundException("User Not Found");
        }

        return new CurrentUserDetails(user);
    }
}
