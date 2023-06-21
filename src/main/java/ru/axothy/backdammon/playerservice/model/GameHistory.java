package ru.axothy.backdammon.playerservice.model;

import lombok.Getter;
import lombok.Setter;
import org.bouncycastle.crypto.engines.IDEAEngine;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.IdentityHashMap;

@Getter
@Setter
@Entity
@Table(name = "game_history")
public class GameHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GAME_ID", nullable = false)
    private int gameId;

    @Column(name = "PLAYER_1", nullable = false)
    private String firstPlayerNickname;

    @Column(name = "PLAYER_2", nullable = false)
    private String secondPlayerNickname;

    @Column(name = "GAME_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date gameDate;

    @Column(name = "WINNER", nullable = false)
    private String winnerNickname;

    @Column(name = "BET", nullable = false)
    private int bet;


    @Override
    public String toString() {
        return gameDate + ": " + firstPlayerNickname + " vs " + secondPlayerNickname +
                ". Bet: " + bet + ". Winner: " + winnerNickname;
    }
}
