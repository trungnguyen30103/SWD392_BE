package com.blindbox.controller;

import com.blindbox.request.AuthRequest;
import com.blindbox.response.AuthResponse;
import com.blindbox.security.JwtUtil;
import com.blindbox.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")  // Cho phép client ở địa chỉ này truy cập API
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        try {
            // Xác thực người dùng với username và password
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // Lấy thông tin người dùng từ UserDetailsService
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

            // Tạo JWT token cho người dùng
            String token = jwtUtil.generateToken(userDetails.getUsername());

            // Lấy quyền của người dùng (role)
            String role = userDetails.getAuthorities().iterator().next().getAuthority();

            // Trả về token và role của người dùng
            return new AuthResponse(token, role);
        } catch (Exception e) {
            // Nếu đăng nhập không thành công, ném ngoại lệ
            throw new RuntimeException("Invalid username or password", e);
        }
    }

}
