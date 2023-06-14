package ru.axothy.backdammon.playerservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Getter @Setter
@Entity
public class Ban {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BAN_ID", nullable = false)
    private Long banId;

    @Column(name = "REASON")
    private String reason;

    @Temporal(TemporalType.DATE)
    @Column(name = "BAN_DATE")
    private Date banDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "UNBAN_DATE")
    private Date unbanDate;

    @ManyToOne
    @JoinColumn(name = "PLAYER_ID")
    private Player player;

    @Override
    public String toString() {
        return banDate + ", " + player.getNickname() + ": " + reason + ". Разбан: " + unbanDate;
    }
}
