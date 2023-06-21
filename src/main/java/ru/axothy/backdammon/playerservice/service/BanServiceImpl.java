package ru.axothy.backdammon.playerservice.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.axothy.backdammon.playerservice.model.Ban;
import ru.axothy.backdammon.playerservice.model.Player;
import ru.axothy.backdammon.playerservice.repository.BanRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BanServiceImpl implements BanService {
    @Autowired
    private BanRepository banRepository;

    @Override
    public Ban create(Ban ban) {
        return banRepository.save(ban);
    }

    @Override
    public void delete(int banId) {
        Ban ban = getBanById(banId);
        banRepository.delete(ban);
    }

    @Override
    public Ban getBanById(int banId) {
        return banRepository.findById(banId).get();
    }

    @Override
    public Page<Ban> getLatestBans(int page, int size) {
        return banRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Ban> getBansByPlayer(Player player) {
        return Lists.newArrayList(banRepository.findByPlayer(player));
    }

    @Override
    public List<Ban> getBansByDate(Date date) {
        return Lists.newArrayList(banRepository.findByBanDate(date));
    }
}
