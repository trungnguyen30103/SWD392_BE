package com.blindbox.service.impl;

import com.blindbox.model.User;
import com.blindbox.repository.UserRepository;
import com.blindbox.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Constructor injection (bạn cũng có thể để Spring quản lý này thông qua @Autowired)
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Lấy người dùng từ UserRepository và trả về Optional<User>
        Optional<User> user = userRepository.findByUserName(username);

        // Nếu không tìm thấy người dùng, ném ra ngoại lệ
        return user.map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
    }
}
