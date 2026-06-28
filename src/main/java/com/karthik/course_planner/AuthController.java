package com.karthik.course_planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        boolean matches = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (matches) {
            return jwtUtil.generateToken(user.getId());
        } else {
            throw new UnauthorizedException("Invalid credentials");
        }
    }

}
