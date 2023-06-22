package ru.axothy.backdammon.playerservice;

import org.junit.Before;
import org.junit.Test;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.axothy.backdammon.playerservice.model.GameHistory;
import ru.axothy.backdammon.playerservice.model.Player;

import java.util.List;

public class RestTestHistory {
    private static final Logger logger = LoggerFactory.getLogger(RestTestClient.class);

    private static final String URL_GET_GAME_BY_ID = "http://localhost:8081/history/game/1";
    private static final String URL_GET_LATEST = "http://localhost:8081/history/latest";
    private static final String URL_ADD_GAME_TO_HISTORY = "http://localhost:8081/history/add";
    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void findById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminToken());
        HttpEntity request = new HttpEntity(headers);

        GameHistory game = restTemplate.exchange(URL_GET_GAME_BY_ID, HttpMethod.GET, request, GameHistory.class).getBody();

        gameInfo(game);
    }
    @Test
    public void latest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminToken());
        HttpEntity request = new HttpEntity(headers);

        List<GameHistory> history = restTemplate.exchange(URL_GET_LATEST, HttpMethod.GET, request, List.class).getBody();
    }

    @Test
    public void addGame() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL_ADD_GAME_TO_HISTORY)
                .queryParam("nickname1", "axothy1")
                .queryParam("nickname2", "axothy2")
                .queryParam("winner", "axothy1")
                .queryParam("bet", "100");

        HttpEntity request = new HttpEntity(headers);

        GameHistory game = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, request, GameHistory.class).getBody();
    }


    public static void gameInfo(GameHistory game) {
        logger.info(game.toString());
    }

    private String getAdminToken() {
        Keycloak keycloak = KeycloakBuilder.builder().serverUrl("http://95.163.242.217:8080")
                .grantType("password")
                .realm("backdammon-realm")
                .clientId("player-service")
                .clientSecret("00c058ea-55d3-4254-ac1f-fd2bbf875cc8")
                .username("admin")
                .password("Maver2281337")
                .build();

        return keycloak.tokenManager().getAccessTokenString();
    }
}
