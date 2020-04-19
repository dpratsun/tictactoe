package ru.job4j.tictactoe.player;

import ru.job4j.tictactoe.cell.Mark;

public interface Player {
    String getName();

    Mark getMark();

    void makeMove();
}