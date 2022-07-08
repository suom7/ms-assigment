package com.ms.authservice.services;

import com.ms.authservice.dto.LoginRequest;
import com.ms.authservice.dto.RegisterRequest;
import com.ms.authservice.dto.StudentDto;
import com.ms.authservice.dto.UserDto;
import com.ms.authservice.entities.User;
import com.ms.authservice.exceptions.InvalidArgumentException;
import com.ms.authservice.exceptions.ObjectNotFoundException;
import com.ms.authservice.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.CharBuffer;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final StudentRestConsumer studentRestConsumer;

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public UserDto login(LoginRequest request) {
        var user = userRepository.findByStudentId(request.getStudentId())
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));

        if (passwordEncoder.matches(CharBuffer.wrap(request.getPassword()), user.getPassword())) {
            UserDto dto = new UserDto();
            dto.setId(user.getId());
            dto.setStudentId(user.getStudentId());
            dto.setToken(createToken(user));
            return dto;
        }

        throw new InvalidArgumentException("Invalid password");
    }

    public UserDto validateToken(String token) {
        String login = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        Optional<User> userOptional = userRepository.findByStudentId(login);

        if (userOptional.isEmpty()) {
            throw new ObjectNotFoundException("User not found");
        }

        User user = userOptional.get();
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setStudentId(user.getStudentId());
        dto.setToken(createToken(user));
        return dto;
    }

    private String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getStudentId());

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public UserDto register(RegisterRequest request) {

        if (Objects.isNull(request)
                || Objects.isNull(request.getEmail())
                || Objects.isNull(request.getStudentId())
                || Objects.isNull(request.getName())
                || Objects.isNull(request.getPhoneNumber())
                || Objects.isNull(request.getPassword())) {
            throw new InvalidArgumentException("Missing mandatory fields");
        }

        var userOptional = userRepository.findByStudentId(request.getStudentId());
        if (userOptional.isPresent()) {
            throw new InvalidArgumentException("User already in database");
        }

        User user = new User();
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(request.getPassword())));
        user.setStudentId(request.getStudentId());
        userRepository.save(user);

        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(request.getStudentId());
        studentDto.setName(request.getName());
        studentDto.setEmail(request.getEmail());
        studentDto.setPhoneNumber(request.getPhoneNumber());
        studentDto.setPortraitPhotoUrl(request.getPortraitPhotoUrl());
        studentRestConsumer.create(studentDto);

        return UserDto.builder()
                .studentId(user.getStudentId())
                .build();
    }
}
