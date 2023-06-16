package ru.axothy.backdammon.playerservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.axothy.backdammon.playerservice.model.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    Page<Player> findAll(Pageable pageable);
    public Player findByPhoneNumber(String phoneNumber);
    public Player findByNickname(String nickname);
}
