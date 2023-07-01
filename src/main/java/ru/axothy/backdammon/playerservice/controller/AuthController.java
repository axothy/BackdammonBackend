package ru.axothy.backdammon.playerservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.axothy.backdammon.playerservice.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping(value = "/auth/login", params = {"username", "password"})
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        authService.login(username, password);
        return ResponseEntity.status(HttpStatus.OK).body("AUTHORIZED SUCCESSFULLY");
    }
}
