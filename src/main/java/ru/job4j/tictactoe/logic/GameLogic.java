package ru.job4j.tictactoe.logic;

import ru.job4j.tictactoe.policy.WinPolicy;
import ru.job4j.tictactoe.table.GameTable;
import ru.job4j.tictactoe.table.Mark;

public class GameLogic implements Logic {
    private final GameTable table;
    private final WinPolicy winPolicy;

    public GameLogic(GameTable table, WinPolicy winPolicy) {
        this.table = table;
        this.winPolicy = winPolicy;
    }

    @Override
    public boolean isWin(Mark mark) {
        return winPolicy.isWin(mark);
    }

    @Override
    public void makeMove(int x, int y, Mark mark) {
        var cell = table.getCell(x, y);
        cell.setMark(mark);
    }
}
