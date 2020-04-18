package ru.job4j.tictactoe.player.service;

import ru.job4j.tictactoe.table.Mark;

public interface HumanService extends PlayerService {
    String getInput();

    void makeMove(int x, int y, Mark mark);
}
