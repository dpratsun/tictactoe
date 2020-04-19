package ru.job4j.tictactoe.cell.impl;

import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.cell.CellStorage;

import java.util.ArrayList;
import java.util.List;

public class MemoryCellStorage implements CellStorage {
    private final List<Cell> cells = new ArrayList<>();

    @Override
    public void add(Cell cell) {
        cells.add(cell);
    }

    @Override
    public Cell find(Cell cell) {
        return cells.get(cells.indexOf(cell));
    }

    @Override
    public List<Cell> findAll() {
        return cells;
    }
}
