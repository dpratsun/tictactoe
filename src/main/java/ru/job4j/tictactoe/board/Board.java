package ru.job4j.tictactoe.board;

import ru.job4j.tictactoe.cell.Cell;

public interface Board extends BoardSize, BoardCells, BoardCell {
    void update(Cell cell);

    void reset();
}
