package com.ndgndg91.todo.user.service;

import com.ndgndg91.todo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ndgndg91.todo.Role.USER;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDetailsManager userDetailsManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(String username, String password){
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(USER.name());
        UserDetails userDetails = new User(username, password, authorities);
        // spring security user creation
        userDetailsManager.createUser(userDetails);
        // business user creation
        password = passwordEncoder.encode(password);
        com.ndgndg91.todo.user.model.User user = com.ndgndg91.todo.user.model.User.builder()
                .username(username)
                .password(password)
                .role(USER)
                .build();
        userRepository.createUser(user);
    }
}
