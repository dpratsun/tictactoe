package ru.job4j.tictactoe.table;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameDataTable implements GameTable {
    public static final int DEFAULT_SIZE = 3;
    private final Cell[][] cells;

    public GameDataTable(final int tableSize) {
        this.cells = new Cell[tableSize][tableSize];
        initMatrix();
    }

    @Override
    public int getSize() {
        return cells.length;
    }

    @Override
    public Cell getCell(final int x, final int y) {
        checkIndexes(x, y);
        return cells[x][y];
    }

    @Override
    public List<Cell> getCellsByMark(final Mark mark) {
        return Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .filter(cell -> mark.equals(cell.getMark()))
                .collect(Collectors.toList());
    }

    @Override
    public void reset() {
        initMatrix();
    }

    private void checkIndexes(final int x, final int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Index should be grater than 0!");
        }
        if (x >= cells.length || y >= cells.length) {
            throw new IllegalArgumentException(String.format("Index should be lower than %s!", cells.length));
        }
    }

    private void initMatrix() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j] = new Cell();
            }
        }
    }
}
