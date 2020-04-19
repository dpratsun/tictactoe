package ru.job4j.tictactoe.player.provider.impl;

import ru.job4j.tictactoe.player.Player;
import ru.job4j.tictactoe.player.provider.PlayerProvider;
import ru.job4j.tictactoe.player.storage.PlayerStorage;

public class CurrentPlayerProvider implements PlayerProvider {
    private final PlayerStorage storage;

    private Player player = null;

    public CurrentPlayerProvider(PlayerStorage storage) {
        this.storage = storage;
    }

    @Override
    public Player get() {
        return player;
    }

    @Override
    public void change() {
        player = storage.getAll()
                .stream()
                .filter(p -> !p.equals(player))
                .findFirst()
                .get();
    }
}
