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
    public GameHistory create(GameHistory gameHistory) {
        return gameHistoryRepository.save(gameHistory);
    }

    @Override
    public void delete(Long gameId) {
        GameHistory game = getGameById(gameId);
        gameHistoryRepository.delete(game);
    }

    @Override
    public GameHistory getGameById(Long id) {
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
