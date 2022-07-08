package com.ms.authservice.web;

import com.ms.authservice.dto.LoginRequest;
import com.ms.authservice.dto.RegisterRequest;
import com.ms.authservice.dto.UserDto;
import com.ms.authservice.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserControllers {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDto> signIn(@RequestBody LoginRequest request) {
        log.info("Trying to login {}", request.getStudentId());
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/validateToken")
    public ResponseEntity<UserDto> validateToken(@RequestParam String token) {
        log.info("Trying to validate token {}", token);
        return ResponseEntity.ok(userService.validateToken(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest request) {
        log.info("Creating new user {}", request.getStudentId());
        UserDto user = userService.register(request);
        return ResponseEntity.ok(user);
    }
}
