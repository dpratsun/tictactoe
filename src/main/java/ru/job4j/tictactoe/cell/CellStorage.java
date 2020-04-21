package ru.job4j.tictactoe.cell;

import java.util.List;

public interface CellStorage {
    void add(Cell cell);

    Cell find(Cell cell);

    void update(Cell cell);

    List<Cell> findAll();

    void clear();
}
