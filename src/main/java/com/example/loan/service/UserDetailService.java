package com.example.loan.service;

import com.example.loan.reponsitory.UserReponsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
    public final UserReponsitory userReponsitory;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userReponsitory.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found" +  username));
    }
}
