package ru.job4j.tictactoe.player.provider;

import ru.job4j.tictactoe.player.Player;

public interface PlayerProvider {
    Player get();

    void change();
}
