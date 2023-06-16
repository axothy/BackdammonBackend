package ru.axothy.backdammon.playerservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FRIEND_ID", nullable = false)
    private Long friendId;

    @Column(name = "REAL_NICKNAME", nullable = false)
    private String realNickname;

    @Column(name = "REAL_ID", nullable = false)
    private Long realId;
}
