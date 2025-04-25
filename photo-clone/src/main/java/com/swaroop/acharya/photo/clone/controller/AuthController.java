package com.swaroop.acharya.photo.clone.controller;
import com.swaroop.acharya.photo.clone.dto.AuthRequest;
import com.swaroop.acharya.photo.clone.dto.AuthResponse;
import com.swaroop.acharya.photo.clone.utils.JwtUtil;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil){
        this.jwtUtil=jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> generate(@RequestBody AuthRequest authRequest){

        if("user123".equals(authRequest.getUserName()) && "password".equals(authRequest.getPassword())){
            String token = jwtUtil.generateToken(authRequest.getUserName());
            return ResponseEntity.ok(new AuthResponse(token));
        }else{
            return  ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validate(@RequestHeader("Authorization") String authorization){
        if(authorization==null ||  !authorization.startsWith("Bearer")){
            return ResponseEntity.status(400).body("Invalid Authorization header");
        }
        String token = authorization.substring(7);
        if(jwtUtil.validate(token))return ResponseEntity.status(200).body(jwtUtil.extractSubject(token));
        return ResponseEntity.status(401).body("Invalid token");
    }

}