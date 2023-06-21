package ru.axothy.backdammon.playerservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.axothy.backdammon.playerservice.model.Ban;
import ru.axothy.backdammon.playerservice.model.Player;

import java.util.Date;
import java.util.List;

@Repository
public interface BanRepository extends CrudRepository<Ban, Integer> {
    Page<Ban> findAll(Pageable pageable);
    List<Ban> findByPlayer(Player player);

    List<Ban> findByBanDate(Date date);
}
