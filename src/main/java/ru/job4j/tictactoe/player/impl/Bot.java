package ru.job4j.tictactoe.player.impl;

import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.cell.CellStorage;
import ru.job4j.tictactoe.player.Player;
import ru.job4j.tictactoe.cell.Mark;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static ru.job4j.tictactoe.cell.Mark.Empty;

public class Bot implements Player {
    private final String name;
    private final Mark mark;
    private final CellStorage storage;

    public Bot(Mark mark, String name, CellStorage storage) {
        this.mark = mark;
        this.name = name;
        this.storage = storage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Mark getMark() {
        return mark;
    }

    @Override
    public void makeMove() {
        List<Cell> cells = storage.findAll()
                .stream()
                .filter(cell -> Empty.equals(cell.getMark()))
                .collect(Collectors.toList());
        var cell = cells.get(getCellIndex(cells.size()));
        cell.setMark(mark);
    }

    private int getCellIndex(int listSize) {
        return listSize > 1 ? new Random().nextInt(listSize - 1) : 0;
    }
}
