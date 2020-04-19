package ru.job4j.tictactoe.logic.impl;

import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.cell.CellStorage;
import ru.job4j.tictactoe.cell.Mark;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.policy.WinPolicy;

import java.util.stream.Collectors;

import static java.lang.String.format;
import static ru.job4j.tictactoe.cell.Mark.Empty;
import static ru.job4j.tictactoe.messages.Messages.INDEX_INPUT_ERROR;

public class GameLogic implements Logic {
    public final static int DEFAULT_BOARD_SIZE = 3;
    private final CellStorage storage;
    private final WinPolicy winPolicy;
    private final int size;

    public GameLogic(CellStorage storage, WinPolicy winPolicy, int size) {
        this.storage = storage;
        this.winPolicy = winPolicy;
        this.size = size;

        init();
    }

    private void init() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                storage.add(new Cell(x, y));
            }
        }
    }

    @Override
    public boolean isWin(Mark mark) {
        return winPolicy.isWin(mark);
    }

    @Override
    public boolean isMoveAvailable() {
        return !(storage.findAll()
                .stream()
                .filter(cell -> Empty.equals(cell.getMark())).count() == 0);
    }

    @Override
    public void makeMove(Cell cell) {
        try {
            var result = storage.find(cell);
            if (!Empty.equals(result.getMark())) {
                throw new IllegalArgumentException("Cell already marked!");
            }
            result.setMark(cell.getMark());
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(INDEX_INPUT_ERROR.getValue(String.valueOf(size - 1)));
        }
    }

    @Override
    public void reset() {
        var cells = storage.findAll();
        cells.forEach(cell -> cell.setMark(Empty));
    }
}
