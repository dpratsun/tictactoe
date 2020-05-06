package ru.job4j.tictactoe.logic.impl;

import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.logic.Mark;
import ru.job4j.tictactoe.policy.WinPolicy;

import java.util.Arrays;

import static java.lang.String.valueOf;
import static ru.job4j.tictactoe.logic.Mark.Empty;
import static ru.job4j.tictactoe.message.Message.CELL_MARKED_ERROR_MESSAGE;
import static ru.job4j.tictactoe.message.Message.INDEX_INPUT_ERROR_MESSAGE;

public class TicTacToeLogic implements Logic {
    public final static int DEFAULT_BOARD_SIZE = 3;
    private final WinPolicy winPolicy;
    private Mark[][] gameBoard;

    public TicTacToeLogic(WinPolicy winPolicy, int size) {
        this.winPolicy = winPolicy;
        resetGameBoard(size);
    }

    @Override
    public boolean isWin(Mark mark) {
        return winPolicy.isWin(mark, this);
    }

    @Override
    public boolean isEmptyMarkPresent() {
        return Arrays.stream(gameBoard)
                .flatMap(marks -> Arrays.stream(marks))
                .anyMatch(Empty::equals);
    }

    @Override
    public void setMark(int x, int y, Mark mark) {
        try {
            Mark foundMark = gameBoard[x][y];
            if (!Empty.equals(foundMark)) {
                throw new IllegalArgumentException(CELL_MARKED_ERROR_MESSAGE.getValue());
            }
            gameBoard[x][y] = mark;
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(INDEX_INPUT_ERROR_MESSAGE.getValue(valueOf(gameBoard.length - 1)));
        }
    }

    @Override
    public int getBoardSize() {
        return gameBoard.length;
    }

    @Override
    public Mark getMark(int x, int y) {
        return gameBoard[x][y];
    }

    @Override
    public void resetGameBoard(int size) {
        gameBoard = new Mark[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                gameBoard[x][y] = Empty;
            }
        }
    }
}
