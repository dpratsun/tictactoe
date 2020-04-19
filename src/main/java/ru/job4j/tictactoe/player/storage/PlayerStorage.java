package ru.job4j.tictactoe.player.storage;

import ru.job4j.tictactoe.player.Player;

import java.util.List;

public interface PlayerStorage {
    void add(Player player);

    List<Player> getAll();
}
