package ru.axothy.backdammon.playerservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYER_ID", nullable = false)
    private Long id;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "NICKNAME", nullable = false)
    private String nickname;

    @Column(name = "NUMBER_OF_WINS", nullable = false)
    private int wins;

    @Column(name = "NUMBER_OF_LOSES", nullable = false)
    private int loses;

    @Column(name = "BALANCE", nullable = false)
    private int balance;

    @Column(name = "BANS_COUNT", nullable = false)
    private int bansCount;

    @Temporal(TemporalType.DATE)
    @Column(name = "REGISTRATION_DATE", nullable = false)
    private Date registrationDate;

    @OneToMany(mappedBy = "players", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ban> bans = new HashSet<>();
}
