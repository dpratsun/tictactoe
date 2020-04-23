package ru.job4j.tictactoe.cell.impl;

import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.cell.CellStorage;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.tictactoe.message.Message.CELL_ADDED_ERROR_MESSAGE;

public class MemoryCellStorage implements CellStorage {
    private final List<Cell> cells = new ArrayList<>();

    @Override
    public void add(Cell cell) {
        var index = cells.indexOf(cell);
        if (index > -1) {
            throw new IllegalArgumentException(CELL_ADDED_ERROR_MESSAGE.getValue());
        }
        cells.add(cell);
    }

    @Override
    public Cell find(Cell cell) {
        return cells.get(cells.indexOf(cell));
    }

    @Override
    public void update(Cell cell) {
        var foundCell = find(cell);
        foundCell.setMark(cell.getMark());
    }

    @Override
    public List<Cell> findAll() {
        return cells;
    }

    @Override
    public void clear() {
        cells.clear();
    }
}
