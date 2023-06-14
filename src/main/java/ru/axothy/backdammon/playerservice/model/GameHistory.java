package ru.axothy.backdammon.playerservice.model;

import lombok.Getter;
import lombok.Setter;
import org.bouncycastle.crypto.engines.IDEAEngine;

import javax.persistence.*;
import java.sql.Date;
import java.util.IdentityHashMap;

enum Winner {FIRST, SECOND}

@Getter
@Setter
public class GameHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GAME_ID")
    private Long gameId;

    @Column(name = "PLAYER_1")
    private String firstPlayerNickname;

    @Column(name = "PLAYER_2")
    private String secondPlayerNickname;

    @Column(name = "GAME_DATE")
    @Temporal(TemporalType.DATE)
    private Date gameDate;

    @Column(name = "WHO_WON")
    private Winner winner;


    @Override
    public String toString() {
        return gameDate + ": " + firstPlayerNickname + " vs " + secondPlayerNickname +
                ". Winner: " + (winner == Winner.FIRST ? firstPlayerNickname : secondPlayerNickname);
    }
}
