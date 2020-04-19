package ru.job4j.tictactoe.state;

public interface StateStorage {
    State get(String className);
}
