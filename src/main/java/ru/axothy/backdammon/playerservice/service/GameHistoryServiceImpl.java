package ru.axothy.backdammon.playerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.axothy.backdammon.playerservice.model.GameHistory;
import ru.axothy.backdammon.playerservice.repository.GameHistoryRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GameHistoryServiceImpl implements GameHistoryService {
    @Autowired
    private GameHistoryRepository gameHistoryRepository;

    @Override
    public GameHistory create(String nickname1, String nickname2, String winnerNickname, int bet) {
        GameHistory game = new GameHistory();

        game.setFirstPlayerNickname(nickname1);
        game.setSecondPlayerNickname(nickname2);
        game.setBet(bet);
        game.setWinnerNickname(winnerNickname);
        game.setGameDate(new Date());

        return gameHistoryRepository.save(game);
    }

    @Override
    public long count() {
        return gameHistoryRepository.count();
    }

    @Override
    public void delete(int gameId) {
        GameHistory game = getGameById(gameId);
        gameHistoryRepository.delete(game);
    }

    @Override
    public GameHistory getGameById(int id) {
        return gameHistoryRepository.findById(id).get();
    }

    @Override
    public List<GameHistory> getGamesByNickname(String nickname, int page, int size) {
        List<GameHistory> result = new ArrayList<>();
        Page<GameHistory> resultIfNicknameIsFirst = gameHistoryRepository.findByFirstPlayerNickname(nickname, PageRequest.of(page, size));
        Page<GameHistory> resultIfNicknameIsSecond = gameHistoryRepository.findBySecondPlayerNickname(nickname, PageRequest.of(page, size));

        result.addAll(resultIfNicknameIsFirst.getContent());
        result.addAll(resultIfNicknameIsSecond.getContent());

        return result;
    }

    @Override
    public Page<GameHistory> getGamesByDate(Date date, int page, int size) {
        return gameHistoryRepository.findByGameDate(date, PageRequest.of(page, size));
    }

    @Override
    public Page<GameHistory> getLatestGames(int page, int size) {
        return gameHistoryRepository.findAll(PageRequest.of(page, size, Sort.by("gameDate")));
    }
}
