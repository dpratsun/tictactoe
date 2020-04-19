package ru.job4j.tictactoe.logic;

import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.cell.Mark;

public interface Logic {
    boolean isWin(Mark mark);

    boolean isMoveAvailable();

    void makeMove(Cell cell);

    void reset();
}
