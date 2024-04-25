package com.turkcell.authserver.services.abstracts;

import com.turkcell.authserver.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void add(User user);
}
