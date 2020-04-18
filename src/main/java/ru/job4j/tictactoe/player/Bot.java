package ru.job4j.tictactoe.player;

import ru.job4j.tictactoe.table.Mark;
import ru.job4j.tictactoe.player.service.BotService;

import java.util.Random;

public class Bot implements Player {
    private final String name;
    private final BotService service;
    private final Mark mark;

    public Bot(Mark mark, String name, BotService service) {
        this.mark = mark;
        this.name = name;
        this.service = service;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void makeMove() {
        var cells = service.getEmptyCells();
        if (cells.isEmpty()) {
            throw new IllegalArgumentException("No available cells to make move!");
        }
        var cell = cells.get(getCellIndex(cells.size()));
        cell.setMark(mark);
    }

    @Override
    public boolean isExitGame() {
        return false;
    }

    @Override
    public Mark getMark() {
        return mark;
    }

    private int getCellIndex(int listSize) {
        return listSize > 1 ? new Random().nextInt(listSize - 1) : 0;
    }
}
