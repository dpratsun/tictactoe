package ru.job4j.tictactoe.board;

import ru.job4j.tictactoe.cell.Cell;

public interface BoardCell {
    Cell get(int x, int y);
}
