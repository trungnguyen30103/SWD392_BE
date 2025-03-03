package com.blindbox.service.impl;

import com.blindbox.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Bạn có thể trả về quyền của người dùng ở đây
        return null;  // Tạm thời trả về null
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
        return true;  // Cần kiểm tra thời gian hết hạn tài khoản, trả về true cho trường hợp này
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Kiểm tra trạng thái khóa tài khoản
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Kiểm tra hết hạn chứng chỉ của người dùng
    }

    @Override
    public boolean isEnabled() {
        return true;  // Kiểm tra trạng thái kích hoạt tài khoản
    }

    public User getUser() {
        return user;
    }
}
