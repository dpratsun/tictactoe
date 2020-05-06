package ru.job4j.tictactoe.logic;

public interface Logic {
    boolean isWin(Mark mark);

    boolean isEmptyMarkPresent();

    void setMark(int x, int y, Mark mark);

    int getBoardSize();

    Mark getMark(int x, int y);

    void resetGameBoard(int size);
}
