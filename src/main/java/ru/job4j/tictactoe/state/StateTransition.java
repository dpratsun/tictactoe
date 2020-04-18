package ru.job4j.tictactoe.state;

public interface StateTransition {
    void add(final String key, final State state);

    State get(final String key);
}
