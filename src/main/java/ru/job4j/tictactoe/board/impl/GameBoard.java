package ru.job4j.tictactoe.board.impl;

import ru.job4j.tictactoe.board.Board;
import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.cell.CellStorage;

import java.util.List;

import static java.lang.String.valueOf;
import static ru.job4j.tictactoe.message.Message.INDEX_INPUT_ERROR_MESSAGE;

public class GameBoard implements Board {
    public final static int DEFAULT_BOARD_SIZE = 3;
    private final CellStorage storage;
    private final int size;

    public GameBoard(CellStorage storage, int size) {
        this.storage = storage;
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<Cell> get() {
        return storage.findAll();
    }

    @Override
    public Cell get(int x, int y) {
        return storage.find(new Cell(x, y));
    }

    @Override
    public void update(Cell cell) {
        try {
            storage.update(cell);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(INDEX_INPUT_ERROR_MESSAGE.getValue(valueOf(size)));
        }
    }

    @Override
    public void reset() {
        storage.clear();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                storage.add(new Cell(x, y));
            }
        }
    }
}
