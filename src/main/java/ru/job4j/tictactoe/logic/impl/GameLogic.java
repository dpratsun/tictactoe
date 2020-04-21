package ru.job4j.tictactoe.logic.impl;

import ru.job4j.tictactoe.board.Board;
import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.cell.Mark;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.policy.WinPolicy;

import static ru.job4j.tictactoe.cell.Mark.Empty;

public class GameLogic implements Logic {
    private final WinPolicy winPolicy;
    private final Board board;

    public GameLogic(WinPolicy winPolicy, Board board) {
        this.board = board;
        this.winPolicy = winPolicy;
    }

    @Override
    public boolean isWin(Mark mark) {
        return winPolicy.isWin(mark);
    }

    @Override
    public boolean isMoveAvailable() {
        return board.get()
                .stream()
                .anyMatch(c -> Empty.equals(c.getMark()));
    }

    @Override
    public void playerMove(Cell cell) {
        board.update(cell);
    }

    @Override
    public void resetBoard() {
        board.reset();
    }
}
