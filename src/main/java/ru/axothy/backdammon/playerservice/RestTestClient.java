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
import ru.axothy.backdammon.playerservice.model.Player;
import ru.axothy.backdammon.playerservice.model.Players;

import java.util.*;

public class RestTestClient {
    private static final Logger logger = LoggerFactory.getLogger(RestTestClient.class);
    private static final String URL_GET_ALL_PLAYERS = "http://localhost:8081/admin/players/list?page=0&size=5";
    private static final String URL_GET_RICHEST_PLAYERS = "http://localhost:8081/players/balancetop?page=0&size=5";
    private static final String URL_GET_BANNED_PLAYERS = "http://localhost:8081/players/banned?page=0&size=5";
    private static final String URL_GET_TOPWINNERS_PLAYERS = "http://localhost:8081/players/topwinners?page=0&size=5";
    private static final String URL_FIND_BY_ID = "http://localhost:8081/players/1";
    private static final String URL_FIND_BY_NICKNAME = "http://localhost:8081/players?nickname=axothy";
    private static final String URL_FIND_BY_PHONE = "http://localhost:8081/admin/players?phone=+79818648398";
    private static final String URL_DELETE_PLAYER_BY_ID = "http://localhost:8081/admin/players/5";

    private static final String URL_CREATE_PLAYER = "http://localhost:8081/admin/players/";
    private static final String URL_UPDATE_PLAYER = "http://localhost:8081/admin/players/";
    private static final String URL_BAN_PLAYER = "http://localhost:8081/admin/players/ban";
    private static final String URL_CHANGE_BALANCE = "http://localhost:8081/admin/players/changebalance";

    private static final String URL_CREATE_NEWBIE = "http://localhost:8081/admin/players/newbie";


    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testFindAll() {
        logger.info("--> Testing findAll paginated:");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminToken());
        HttpEntity request = new HttpEntity(headers);

        Players players = restTemplate.exchange(URL_GET_ALL_PLAYERS, HttpMethod.GET, request, Players.class).getBody();
        listAllPlayers(players.getPlayers());
    }

    @Test
    public void findById() {
        logger.info("--> Testing findPlayerById:");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminToken());
        HttpEntity request = new HttpEntity(headers);

        Player player = restTemplate.exchange(URL_FIND_BY_ID, HttpMethod.GET, request, Player.class).getBody();
        playerInfo(player);
    }

    @Test
    public void testDeleteById() {
        logger.info("--> Testing deleteById:");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminToken());
        HttpEntity request = new HttpEntity(headers);

        restTemplate.exchange(URL_DELETE_PLAYER_BY_ID, HttpMethod.DELETE, request, Void.class);
    }

    @Test
    public void createPlayer() {
        logger.info("--> Testing createPlayer:");
        Player player = new Player();
        player.setNickname("Test2");
        player.setBalance(0);
        player.setWins(0);
        player.setLoses(0);
        player.setBansCount(0);
        player.setPhoneNumber("79818648396");
        player.setRegistrationDate(new Date());
        player.setBans(new HashSet<>());

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Player> requestEntity = new HttpEntity<>(player, headers);

        Player newPlayer = restTemplate.exchange(URL_CREATE_PLAYER, HttpMethod.POST, requestEntity, Player.class).getBody();
        playerInfo(newPlayer);
    }

    @Test
    public void updatePlayer() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminToken());
        HttpEntity request = new HttpEntity(headers);

        Player player = restTemplate.exchange(URL_FIND_BY_ID, HttpMethod.GET, request, Player.class).getBody();
        player.setBalance(2);

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Player> requestEntity = new HttpEntity<>(player, headers);
        Player newPlayer = restTemplate.exchange(URL_UPDATE_PLAYER, HttpMethod.POST, requestEntity, Player.class).getBody();

    }

    @Test
    public void banPlayer() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL_BAN_PLAYER)
                .queryParam("nickname", "axothy1")
                .queryParam("reason", "cheater!")
                .queryParam("unbanDate", "2023-06-22");
        HttpEntity request = new HttpEntity(headers);

        String response = restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, request, String.class).getBody();

    }

    @Test
    public void changeBalance() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL_CHANGE_BALANCE)
                .queryParam("nickname", "axothy1")
                .queryParam("amount", "-15");
        HttpEntity request = new HttpEntity(headers);

        String response = restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, request, String.class).getBody();

    }

    @Test
    public void createNewbie() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL_CREATE_NEWBIE)
                .queryParam("nickname", "axothy_newbie")
                .queryParam("phone", "79818648389");
        HttpEntity request = new HttpEntity(headers);

        Player player = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, request, Player.class).getBody();

    }

    /*
    @Test
    public void testCreate() {
        logger.info("--> Testing create user");
        User newUser = new User();
        newUser.setName("BB");
        newUser.setBirthDate(new Date(
                (new GregorianCalendar(1940, 8, 16)).getTime().getTime()));

        ActiveStatus activeStatus = new ActiveStatus();
        activeStatus.setStatus("test_status");
        newUser.setActiveStatus(activeStatus);
        newUser = restTemplate.postForObject(URL_CREATE_USER, newUser, User.class);
        logger.info("Singer created successfully: " + newUser);
    }

    @Test
    public void testUpdate() {
        logger.info("--> Testing update user");
        User user = restTemplate.getForObject(URL_GET_USER_BY_ID, User.class, 1);
        user.setName("Миша Иванов");

        restTemplate.put(URL_UPDATE_USER, user, 1);
        logger.info("User updated successfully");
    }
     */

    /**
     * Список всех учеников со всеми связями. Для тех, кто будет тестировать через JUnit:
     * можно использовать данный метод в тестах, каждая сущность содержит полноценное
     * описание в toString().
     *
     * @param players
     */
    public static void listAllPlayers(List<Player> players) {
        logger.info(" ---- Listing all players:");
        for (Player player : players) {
            playerInfo(player);
        }
    }

    public static void playerInfo(Player player) {
        logger.info(player.toString());
        if (player.getBans() != null) {
            logger.info("\t" + player.getBans().toString());
        }

        if (player.getFriends() != null) {
            logger.info("\t" + player.getBans().toString());
        }
    }

    private String getAdminToken() {
        Keycloak keycloak = KeycloakBuilder.builder().serverUrl("http://95.163.242.217:8080")
                .grantType("password")
                .realm("backdammon-realm")
                .clientId("player-service")
                .clientSecret("x")
                .username("admin")
                .password("x")
                .build();

        return keycloak.tokenManager().getAccessTokenString();
    }
}