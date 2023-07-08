package ru.axothy.backdammon.playerservice.service;

import org.springframework.data.domain.Page;
import ru.axothy.backdammon.playerservice.model.GameHistory;

import java.util.Date;
import java.util.List;

public interface GameHistoryService {

    GameHistory create(String nickname1, String nickname2, String winnerNickname, int bet);
    void delete(int gameId);

    GameHistory getGameById(int id);

    List<GameHistory> getGamesByNickname(String nickname, int page, int size);

    Page<GameHistory> getGamesByDate(Date date, int page, int size);

    Page<GameHistory> getLatestGames(int page, int size);

    long count();
}
