package ru.axothy.backdammon.playerservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "players")
public class Player implements Serializable {

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

    @OneToMany(mappedBy = "player")
    private Set<Ban> bans = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "players_friends", joinColumns = @JoinColumn(name = "PLAYER_ID"),
    inverseJoinColumns = @JoinColumn(name = "FRIEND_ID"))
    private Set<Friend> friends = new HashSet<>();

    @Override
    public String toString() {
        return "Player " + nickname + ", phone: " + phoneNumber + ", W/L: " + wins + "/" + loses +
                ", balance: " + balance + " coins, " + bans + " bans, registration date: " + registrationDate;
    }
}
