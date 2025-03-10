package com.blindbox.service.impl;

import com.blindbox.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Trả về quyền của người dùng
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getRoleName()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Kiểm tra tài khoản hết hạn
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Kiểm tra tài khoản bị khóa hay không
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Kiểm tra chứng chỉ của người dùng có hết hạn không
    }

    @Override
    public boolean isEnabled() {
        return true;  // Kiểm tra trạng thái tài khoản
    }

    public User getUser() {
        return user;
    }
}
