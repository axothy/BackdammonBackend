package ru.axothy.backdammon.playerservice.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.axothy.backdammon.playerservice.config.ServiceConfig;
import ru.axothy.backdammon.playerservice.model.Ban;
import ru.axothy.backdammon.playerservice.model.Player;
import ru.axothy.backdammon.playerservice.repository.PlayerRepository;

import java.util.*;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ServiceConfig config;

    @Override
    public Player create(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player update(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player getPlayerById(int playerId) {
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
    public void delete(int playerId) {
        Player player = getPlayerById(playerId);
        playerRepository.delete(player);
    }

    @Override
    public Page<Player> getRichestPlayers(int page, int size) {
        return playerRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "balance")));
    }

    @Override
    public Page<Player> getTopPlayersByWins(int page, int size) {
        return playerRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"wins")));
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

    @Override
    public Player createNewbie(String nickname, String phoneNumber) {
        Player player = new Player();
        player.setNickname(nickname);
        player.setPhoneNumber(phoneNumber);
        player.setBalance(0);
        player.setWins(0);
        player.setLoses(0);
        player.setBansCount(0);
        player.setBans(new HashSet<>());
        player.setRegistrationDate(new Date());
        player.setFriends(new HashSet<>());

        playerRepository.save(player);
        return player;
    }

    @Override
    public void banPlayer(int playerId, String reason, Date unbanDate) {
        Player player = getPlayerById(playerId);

        Ban ban = new Ban();
        ban.setPlayer(player);
        ban.setReason(reason);
        ban.setBanDate(new Date());
        ban.setUnbanDate(unbanDate);

        player.setBansCount(player.getBansCount() + 1);
        player.getBans().add(ban);
        playerRepository.save(player);
    }


    @Override
    public void changeBalance(int playerId, int amount) {
        Player player = getPlayerById(playerId);

        player.setBalance(player.getBalance() + amount);

        if (player.getBalance() < 0) player.setBalance(0);

        playerRepository.save(player);
    }

    @Override
    public void addWin(int playerId) {
        Player player = getPlayerById(playerId);
        player.setWins(player.getWins() + 1);
    }

    @Override
    public void addLose(int playerId) {
        Player player = getPlayerById(playerId);
        player.setWins(player.getLoses() + 1);
    }
}
