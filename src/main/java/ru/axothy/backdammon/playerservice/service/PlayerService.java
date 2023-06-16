package ru.axothy.backdammon.playerservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.axothy.backdammon.playerservice.model.Player;
import ru.axothy.backdammon.playerservice.repository.PlayerRepository;

import java.util.List;

public interface PlayerService {
    Player createPlayer(Player player);

    Player getPlayerById(Long playerId);

    Player getPlayerByPhoneNumber(String phoneNumber);

    Player getPlayerByNickname(String nickname);

    void deletePlayer(Long playerId);

    Page<Player> getRichestPlayers(int page, int size);

    Page<Player> getTopPlayersByWins(int page, int size);

    Page<Player> getAllPlayers(int page, int size);

    List<Player> getAllPlayers();

    List<Player> getBannedPlayers(int page, int size);
}
