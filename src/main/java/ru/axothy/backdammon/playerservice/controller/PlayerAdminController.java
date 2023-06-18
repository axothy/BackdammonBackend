package ru.axothy.backdammon.playerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.axothy.backdammon.playerservice.model.Player;
import ru.axothy.backdammon.playerservice.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/players")
public class PlayerAdminController {
    @Autowired
    private PlayerService playerService;

    @GetMapping(params = {"phone"})
    public ResponseEntity<Player> getPlayerByPhoneNumber(@RequestParam("phone") String phone) {
        Player player = playerService.getPlayerByPhoneNumber(phone);

        return ResponseEntity.ok(player);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long playerId) {
        playerService.delete(playerId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public Player create(@RequestBody Player player) {
        playerService.create(player);
        return player;
    }

    @GetMapping(value = "/list", params = {"page", "size"})
    public List<Player> getAllPlayers(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<Player> players = playerService.getAllPlayers(page, size);

        if (page > players.getTotalPages())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to found page " + page);

        return players.getContent();
    }
}
