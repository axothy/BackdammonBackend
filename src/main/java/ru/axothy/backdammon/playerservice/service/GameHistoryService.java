package ru.axothy.backdammon.playerservice.service;

import org.springframework.data.domain.Page;
import ru.axothy.backdammon.playerservice.model.GameHistory;

import java.util.Date;
import java.util.List;

public interface GameHistoryService {

    GameHistory create(GameHistory gameHistory);
    void delete(int gameId);

    GameHistory getGameById(int id);

    List<GameHistory> getGamesByNickname(String nickname, int page, int size);

    Page<GameHistory> getGamesByDate(Date date, int page, int size);

    Page<GameHistory> getLatestGames(int page, int size);
}
