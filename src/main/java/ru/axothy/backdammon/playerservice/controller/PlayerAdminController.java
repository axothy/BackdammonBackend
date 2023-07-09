package ru.axothy.backdammon.playerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.axothy.backdammon.playerservice.model.Player;
import ru.axothy.backdammon.playerservice.service.PlayerService;

import javax.annotation.security.RolesAllowed;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/players")
public class PlayerAdminController {
    @Autowired
    private PlayerService playerService;

    @RolesAllowed("ADMIN")
    @GetMapping(params = {"phone"})
    public ResponseEntity<Player> getPlayerByPhoneNumber(@RequestParam("phone") String phone) {
        Player player = playerService.getPlayerByPhoneNumber(phone);

        return ResponseEntity.ok(player);
    }

    @RolesAllowed("ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int playerId) {
        playerService.delete(playerId);
    }

    @RolesAllowed("ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public Player create(@RequestBody Player player) {
        return playerService.create(player);
    }

    @RolesAllowed("ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/newbie")
    public Player createNewbie(@RequestParam("nickname") String nickname, @RequestParam("phone") String phone) {
        return playerService.createNewbie(nickname, phone);
    }

    @RolesAllowed("ADMIN")
    @PutMapping(value = "/")
    public Player update(@RequestBody Player player) {
        return playerService.update(player);
    }

    @RolesAllowed("ADMIN")
    @GetMapping(value = "/list", params = {"page", "size"})
    public List<Player> getAllPlayers(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<Player> players = playerService.getAllPlayers(page, size);

        if (page > players.getTotalPages())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to found page " + page);

        return players.getContent();
    }

    @RolesAllowed("ADMIN")
    @PutMapping(value = "/ban")
    public ResponseEntity<String> banPlayer(@RequestParam("nickname") String nickname, @RequestParam("reason") String reason,
                                            @RequestParam("unbanDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date unbanDate) {
        Player player = playerService.getPlayerByNickname(nickname);
        if (player == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Игрок с таким ником не найден");
        }

        playerService.banPlayer(player.getId(), reason, unbanDate);
        return ResponseEntity.ok("Игрок " + nickname + " забанен");
    }

    @RolesAllowed("ADMIN")
    @PutMapping(value = "/changebalance", params = {"nickname", "amount"})
    public ResponseEntity<String> changeBalance(@RequestParam("nickname") String nickname, @RequestParam("amount") int amount) {
        Player player = playerService.getPlayerByNickname(nickname);
        if (player == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Игрок с таким ником не найден");
        }

        playerService.changeBalance(player.getId(), amount);
        return ResponseEntity.ok("Игроку " + nickname + " начислено " + amount + " монет");
    }


}
