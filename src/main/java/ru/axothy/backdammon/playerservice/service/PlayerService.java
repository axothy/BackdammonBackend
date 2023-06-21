package ru.axothy.backdammon.playerservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.axothy.backdammon.playerservice.model.Friend;
import ru.axothy.backdammon.playerservice.model.Player;
import ru.axothy.backdammon.playerservice.repository.PlayerRepository;

import java.util.Date;
import java.util.List;

public interface PlayerService {
    Player create(Player player);

    Player createNewbie(String nickname, String phoneNumber);

    Player update(Player player);

    Player getPlayerById(int playerId);

    Player getPlayerByPhoneNumber(String phoneNumber);

    Player getPlayerByNickname(String nickname);

    void delete(int playerId);

    void banPlayer(int playerId, String reason, Date unbanDate);

    void changeBalance(int playerId, int amount);

    void addWin(int playerId);

    void addLose(int playerId);

    Page<Player> getRichestPlayers(int page, int size);

    Page<Player> getTopPlayersByWins(int page, int size);

    Page<Player> getAllPlayers(int page, int size);

    List<Player> getAllPlayers();

    List<Player> getBannedPlayers(int page, int size);
}
