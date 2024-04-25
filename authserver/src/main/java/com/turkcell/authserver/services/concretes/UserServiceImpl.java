package com.turkcell.authserver.services.concretes;

import com.turkcell.authserver.entities.User;
import com.turkcell.authserver.repositories.UserRepository;
import com.turkcell.authserver.services.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Spring Security
        return userRepository.findByEmail(username).orElseThrow(() -> new BadCredentialsException(""));
    }
}
