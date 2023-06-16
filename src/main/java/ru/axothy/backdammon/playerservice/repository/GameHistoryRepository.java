package ru.axothy.backdammon.playerservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.axothy.backdammon.playerservice.model.GameHistory;

import java.util.Date;
import java.util.List;

@Repository
public interface GameHistoryRepository extends CrudRepository<GameHistory, Long> {
    Page<GameHistory> findByFirstPlayerNickname(String nickname, Pageable pageable);
    Page<GameHistory> findBySecondPlayerNickname(String nickname, Pageable pageable);
    Page<GameHistory> findByDate(Date date, Pageable pageable);

    Page<GameHistory> findAll(Pageable pageable);
}
