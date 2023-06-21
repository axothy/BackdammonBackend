package ru.axothy.backdammon.playerservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "bans")
public class Ban implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BAN_ID", nullable = false)
    private int banId;

    @Column(name = "REASON")
    private String reason;

    @Temporal(TemporalType.DATE)
    @Column(name = "BAN_DATE", nullable = false)
    private Date banDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "UNBAN_DATE", nullable = false)
    private Date unbanDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PLAYER_ID")
    @JsonBackReference
    private Player player;

    @Override
    public String toString() {
        return banDate + ", " + player.getNickname() + ": " + reason + ". Разбан: " + unbanDate;
    }
}
