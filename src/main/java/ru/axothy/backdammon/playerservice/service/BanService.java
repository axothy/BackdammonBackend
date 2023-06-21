package ru.axothy.backdammon.playerservice.service;

import org.springframework.data.domain.Page;
import ru.axothy.backdammon.playerservice.model.Ban;
import ru.axothy.backdammon.playerservice.model.Player;

import java.util.Date;
import java.util.List;

public interface BanService {

    Ban create(Ban ban);
    void delete(int banId);
    Ban getBanById(int banId);
    Page<Ban> getLatestBans(int page, int size);
    List<Ban> getBansByPlayer(Player player);

    List<Ban> getBansByDate(Date date);
}
