package com.example.loan.configuration;

import com.example.loan.entities.User;
import com.example.loan.reponsitory.UserReponsitory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
public class ApplicationInitConfig {

    @Bean
    ApplicationRunner applicationRunner(UserReponsitory userReponsitory){
        return args -> {
            if (userReponsitory.findByEmail("admin").isEmpty()) {
                var roles = new HashSet<String>();
                roles.add("ADMIN");
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                User user = User.builder()
                        .email("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .build();
                userReponsitory.save(user);
            }
        };
    }

}
