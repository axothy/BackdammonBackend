package ru.axothy.backdammon.playerservice.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Players implements Serializable {
    private List<Player> players;

    public Players() {

    }

    public Players(List<Player> players) {
        this.players = players;
    }
}
