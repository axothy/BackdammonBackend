package ru.axothy.backdammon.playerservice.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.axothy.backdammon.playerservice.config.ServiceConfig;
import ru.axothy.backdammon.playerservice.model.Ban;
import ru.axothy.backdammon.playerservice.model.Friend;
import ru.axothy.backdammon.playerservice.model.Player;
import ru.axothy.backdammon.playerservice.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ServiceConfig config;

    @Override
    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player getPlayerById(Long playerId) {
        return playerRepository.findById(playerId).get();
    }

    @Override
    public Player getPlayerByPhoneNumber(String phoneNumber) {
        return playerRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Player getPlayerByNickname(String nickname) {
        return playerRepository.findByNickname(nickname);
    }

    @Override
    public void deletePlayer(Long playerId) {
        Player player = getPlayerById(playerId);
        playerRepository.delete(player);
    }

    @Override
    public Page<Player> getRichestPlayers(int page, int size) {
        return playerRepository.findAll(PageRequest.of(page, size, Sort.by("balance")));
    }

    @Override
    public Page<Player> getTopPlayersByWins(int page, int size) {
        return playerRepository.findAll(PageRequest.of(page, size, Sort.by("wins")));
    }

    @Override
    public Page<Player> getAllPlayers(int page, int size) {
        return playerRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Player> getAllPlayers() {
        return Lists.newArrayList(playerRepository.findAll());
    }

    @Override
    public List<Player> getBannedPlayers(int page, int size) {
        Page<Player> players = playerRepository.findAll(PageRequest.of(page, size));
        List<Player> bannedPlayers = new ArrayList<>();

        for (Player player : players) {
            if (player.getBans().size() != 0) bannedPlayers.add(player);
        }

        return bannedPlayers;
    }
}
