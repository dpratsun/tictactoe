package ru.job4j.tictactoe.table;

import java.util.List;

public interface GameTable {
    int getSize();

    Cell getCell(int x, int y);

    List<Cell> getCellsByMark(Mark mark);

    void reset();
}
