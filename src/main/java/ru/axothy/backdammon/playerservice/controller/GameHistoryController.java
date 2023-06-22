package ru.axothy.backdammon.playerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.axothy.backdammon.playerservice.model.GameHistory;
import ru.axothy.backdammon.playerservice.service.GameHistoryService;

import javax.annotation.security.RolesAllowed;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/history")
public class GameHistoryController {
    @Autowired
    private GameHistoryService historyService;

    @RolesAllowed({"PLAYER", "ADMIN"})
    @GetMapping(value = "/game/{id}")
    public GameHistory getLatest(@PathVariable int id) {
        return historyService.getGameById(id);
    }

    @RolesAllowed({"PLAYER", "ADMIN"})
    @GetMapping(value = "/latest", params = {"page", "size"})
    public List<GameHistory> getLatest(@RequestParam("page") int page, @RequestParam("size") int size) {
        return historyService.getLatestGames(page, size).getContent();
    }

    @RolesAllowed({"PLAYER", "ADMIN"})
    @GetMapping(value = "/player", params = {"page", "size"})
    public List<GameHistory> getPlayerGames(@PathVariable("page") int page, @PathVariable("size") int size,
                                            @RequestParam("nickname") String nickname) {
        return historyService.getGamesByNickname(nickname, page, size);
    }

    @RolesAllowed("ADMIN")
    @PostMapping(value = "/add")
    public GameHistory addGame(@RequestParam String nickname1, @RequestParam String nickname2,
                               @RequestParam String winner, @RequestParam int bet) {
        return historyService.create(nickname1, nickname2, winner, bet);
    }

    @RolesAllowed({"PLAYER", "ADMIN"})
    @PostMapping(value = "/date")
    public List<GameHistory> getGamesByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                            @PathVariable("page") int page, @PathVariable("size") int size) {
        return historyService.getGamesByDate(date, page, size).getContent();
    }

}
