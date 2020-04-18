package ru.job4j.tictactoe.player.service;

import ru.job4j.tictactoe.table.Cell;
import ru.job4j.tictactoe.table.GameTable;
import ru.job4j.tictactoe.table.Mark;

import java.util.List;

public class BotServiceImpl implements BotService {
    private final GameTable table;

    public BotServiceImpl(GameTable table) {
        this.table = table;
    }

    @Override
    public List<Cell> getEmptyCells() {
        return table.getCellsByMark(Mark.Empty);
    }

    @Override
    public void print(String message) {
    }
}
