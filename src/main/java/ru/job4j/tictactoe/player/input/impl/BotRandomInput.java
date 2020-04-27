package ru.job4j.tictactoe.player.input.impl;

import ru.job4j.tictactoe.board.BoardCells;
import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.player.input.PlayerInput;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static ru.job4j.tictactoe.cell.Mark.Empty;

public class BotRandomInput implements PlayerInput {
    private final BoardCells cells;
    private final Random random;

    public BotRandomInput(BoardCells cells, Random random) {
        this.cells = cells;
        this.random = random;
    }

    @Override
    public Cell get() {
        List<Cell> cells = this.cells.get()
                .stream()
                .filter(c -> Empty.equals(c.getMark()))
                .collect(Collectors.toList());
        var foundCell = cells.get(getCellIndex(cells.size()));

        return new Cell(foundCell.getX(), foundCell.getY());
    }

    private int getCellIndex(int listSize) {
        return listSize > 1 ? random.nextInt(listSize - 1) : 0;
    }
}
