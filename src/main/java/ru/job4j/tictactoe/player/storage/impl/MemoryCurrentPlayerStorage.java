package ru.job4j.tictactoe.player.storage.impl;

import ru.job4j.tictactoe.player.Player;
import ru.job4j.tictactoe.player.provider.CurrentPlayerProvider;
import ru.job4j.tictactoe.player.storage.CurrentPlayerStorage;
import ru.job4j.tictactoe.player.storage.PlayerStorage;

public class MemoryCurrentPlayerStorage implements CurrentPlayerStorage, CurrentPlayerProvider {
    private final PlayerStorage storage;

    private Player player = null;

    public MemoryCurrentPlayerStorage(PlayerStorage storage) {
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
