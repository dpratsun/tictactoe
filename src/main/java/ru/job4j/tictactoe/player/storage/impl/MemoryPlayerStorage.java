package ru.job4j.tictactoe.player.storage.impl;

import ru.job4j.tictactoe.player.Player;
import ru.job4j.tictactoe.player.storage.PlayerStorage;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.tictactoe.message.Message.ADD_PLAYER_ERROR_MESSAGE;

public class MemoryPlayerStorage implements PlayerStorage {
    private final List<Player> players = new ArrayList<>();

    @Override
    public void add(Player player) {
        for (var el: players) {
            if (el.getMark().equals(player.getMark())) {
                throw new IllegalArgumentException(ADD_PLAYER_ERROR_MESSAGE.getValue());
            }
        }
        players.add(player);
    }

    @Override
    public List<Player> getAll() {
        return players;
    }
}
