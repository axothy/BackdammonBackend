package ru.axothy.backdammon.playerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.axothy.backdammon.playerservice.model.Player;
import ru.axothy.backdammon.playerservice.service.PlayerService;
import org.springframework.http.HttpStatus;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(value = "/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @RolesAllowed({"PLAYER", "ADMIN"})
    @GetMapping(value = "/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") int playerId) {
        Player player = playerService.getPlayerById(playerId);
        return ResponseEntity.ok(player);
    }

    @RolesAllowed({"PLAYER", "ADMIN"})
    @GetMapping(params = {"nickname"})
    public ResponseEntity<Player> getPlayerByNickname(@RequestParam("nickname") String nickname) {
        Player player = playerService.getPlayerByNickname(nickname);
        return ResponseEntity.ok(player);
    }

    @RolesAllowed({"PLAYER", "ADMIN"})
    @GetMapping(value = "/balancetop", params = {"page", "size"})
    public List<Player> getRichestPlayers(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<Player> richest = playerService.getRichestPlayers(page, size);

        if (page > richest.getTotalPages()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to found page " + page);
        }

        return richest.getContent();
    }

    @RolesAllowed({"ADMIN", "PLAYER"})
    @GetMapping(value = "/banned", params = {"page", "size"})
    public List<Player> getBannedPlayers(@RequestParam("page") int page, @RequestParam("size") int size) {
        return playerService.getBannedPlayers(page, size);

    }

    @RolesAllowed({"ADMIN", "PLAYER"})
    @GetMapping(value = "/balance")
    public int getBalanceByPlayer(@RequestParam String nickname) {
        return playerService.getBalance(nickname);

    }

    @RolesAllowed({"ADMIN", "PLAYER"})
    @GetMapping(value = "/topwinners", params = {"page", "size"})
    public List<Player> getTopWinners(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<Player> topwinners = playerService.getTopPlayersByWins(page, size);

        if (page > topwinners.getTotalPages()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to found page " + page);
        }

        return topwinners.getContent();
    }

}
