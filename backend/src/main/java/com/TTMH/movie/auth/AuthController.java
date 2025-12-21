package com.TTMH.movie.auth;

import com.TTMH.movie.auth.dto.AuthResponse;
import com.TTMH.movie.auth.dto.LoginRequest;
import com.TTMH.movie.auth.dto.RegisterRequest;
import com.TTMH.movie.common.BadRequestException;
import com.TTMH.movie.domain.User;
import com.TTMH.movie.repo.UserRepo;
import com.TTMH.movie.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthController(UserRepo userRepo, BCryptPasswordEncoder encoder, JwtService jwtService) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest req) {
        if(userRepo.existsByEmail(req.getEmail())) {
            throw new BadRequestException("Email đã sử dụng");
        }

        User user = User.builder()
                .email(req.getEmail().toLowerCase())
                .passwordHash(encoder.encode(req.getPassword()))
                .fullName(req.getFullName())
                .role("USER")
                .createAt(Instant.now())
                .build();

        userRepo.save(user);

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token, "Bearer");
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest req) {
        var user = userRepo.findByEmail(req.getEmail().toLowerCase())
                .orElseThrow(() -> new BadRequestException("Email hoặc mật khẩu không đúng"));

        if(!encoder.matches(req.getPassword(), user.getPasswordHash())) {
            throw new BadRequestException("Email hoặc mật khẩu kkhông đúng");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token, "Bearer");
    }
}
