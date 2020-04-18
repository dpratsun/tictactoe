package ru.job4j.tictactoe.state;

import ru.job4j.tictactoe.player.Player;

public interface State {
    State perform();

    default void forPlayer(final Player player) { }
}
