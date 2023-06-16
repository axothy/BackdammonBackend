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

    Player getPlayerById(Long playerId);

    Player getPlayerByPhoneNumber(String phoneNumber);

    Player getPlayerByNickname(String nickname);

    void delete(Long playerId);

    void banPlayer(String nickname, String reason, Date unbanDate);

    Page<Player> getRichestPlayers(int page, int size);

    Page<Player> getTopPlayersByWins(int page, int size);

    Page<Player> getAllPlayers(int page, int size);

    List<Player> getAllPlayers();

    List<Player> getBannedPlayers(int page, int size);
}
