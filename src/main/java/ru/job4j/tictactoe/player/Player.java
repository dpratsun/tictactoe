package ru.job4j.tictactoe.player;

import ru.job4j.tictactoe.logic.Mark;

public interface Player {
    String getName();

    Mark getMark();

    void makeMove();
}