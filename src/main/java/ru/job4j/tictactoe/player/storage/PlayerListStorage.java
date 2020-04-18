package ru.job4j.tictactoe.player.storage;

import ru.job4j.tictactoe.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerListStorage implements PlayerStorage {
    private final List<Player> players = new ArrayList<>();

    @Override
    public void add(Player player) {
        for (var el: players) {
            if (el.getMark().equals(player.getMark())) {
                throw new IllegalArgumentException("Players could not have same Marks!");
            }
        }
        players.add(player);
    }

    @Override
    public List<Player> getAll() {
        return players;
    }

    @Override
    public Player next(Player current) {
        return players.stream()
                .filter(player -> !player.equals(current))
                .findFirst()
                .get();
    }
}
