package ru.axothy.backdammon.playerservice.service;


import lombok.RequiredArgsConstructor;
import lombok.val;
import org.keycloak.authorization.client.AuthzClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final AuthzClient authzClient;

    public void login(String username, String pass) {
        val response = authzClient.authorization(username, pass)
                    .authorize();
    }
}
