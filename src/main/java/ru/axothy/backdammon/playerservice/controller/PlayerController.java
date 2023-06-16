package ru.axothy.backdammon.playerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.axothy.backdammon.playerservice.model.Player;
import ru.axothy.backdammon.playerservice.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping(value = "/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping()
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") Long playerId) {
        Player player = playerService.getPlayerById(playerId);
        return ResponseEntity.ok(player);
    }
}
