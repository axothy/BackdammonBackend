package ru.axothy.backdammon.playerservice;


import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.client.RestTemplate;
import ru.axothy.backdammon.playerservice.model.Player;
import ru.axothy.backdammon.playerservice.model.Players;

import java.util.List;

public class RestTestClient {
    private static final Logger logger = LoggerFactory.getLogger(RestTestClient.class);
    private static final String URL_GET_ALL_PLAYERS = "http://localhost:8080/admin/players/list?page=0&size=5";
    private static final String URL_GET_RICHEST_PLAYERS = "http://localhost:8080/players/balancetop?page=0&size=5";
    private static final String URL_GET_BANNED_PLAYERS = "http://localhost:8080/players/banned?page=0&size=5";
    private static final String URL_GET_TOPWINNERS_PLAYERS = "http://localhost:8080/players/topwinners?page=0&size=5";

    private static final String URL_FIND_BY_ID = "http://localhost:8080/players/1";
    private static final String URL_DELETE_USER_BY_ID = "http://localhost:8080/user/{id}";
    private static final String URL_CREATE_USER = "http://localhost:8080/user/";
    private static final String URL_UPDATE_USER = "http://localhost:8080/user/{id}";
    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testFindAll() {
        logger.info("--> Testing findAll paginated:");
        Players players = restTemplate.getForObject(URL_GET_ALL_PLAYERS, Players.class);
        listAllPlayers(players.getPlayers());
    }

    @Test
    public void findById() {
        logger.info("--> Testing findPlayerById:");
        Player player = restTemplate.getForObject(URL_FIND_BY_ID, Player.class);
        playerInfo(player);
    }
    /*
    @Test
    public void testFindById() {
        logger.info("--> Testing findById:");
        User user = restTemplate.getForObject(URL_GET_USER_BY_ID, User.class, 4);
        logger.info(user.toString());
        Assert.assertEquals(user.getName(), "Коля");
    }

    @Test
    public void testDeleteById() {
        logger.info("--> Testing deleteById:");
        restTemplate.delete(URL_DELETE_USER_BY_ID, 5);
    }

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
}