package ru.job4j.tictactoe.logic;

import ru.job4j.tictactoe.table.Mark;

public interface Logic {
    boolean isWin(Mark mark);

    void makeMove(int x, int y, Mark mark);
}
