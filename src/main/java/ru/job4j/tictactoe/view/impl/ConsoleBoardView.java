package ru.job4j.tictactoe.view.impl;

import ru.job4j.tictactoe.board.BoardCells;
import ru.job4j.tictactoe.board.BoardSize;
import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.view.View;

import java.util.List;

import static java.lang.System.lineSeparator;
import static java.lang.System.out;

public class ConsoleBoardView implements View {
    public final static String O_MARK = " O ";
    public final static String X_MARK = " X ";
    public final static String EMPTY_MARK = "   ";
    public final static String COL_SEPARATOR = "|";
    public final static String ROW_SEPARATOR = "----";
    public final static String LS = lineSeparator();

    private final BoardCells boardCells;
    private final BoardSize boardSize;

    public ConsoleBoardView(BoardCells boardCells, BoardSize boardSize) {
        this.boardCells = boardCells;
        this.boardSize = boardSize;
    }

    @Override
    public void show() {
        List<Cell> cells = boardCells.get();
        for (int i = 0; i < cells.size(); i++) {
            printCell(cells.get(i));
            printSeparator(i, COL_SEPARATOR, LS);
            if (i < cells.size() - 1) {
                printSeparator(i, "", ROW_SEPARATOR.repeat(boardSize.size()) + LS);
            }
        }
        out.println();
    }

    private void printCell(final Cell cell) {
        switch (cell.getMark()) {
            case O -> out.print(O_MARK);
            case X -> out.print(X_MARK);
            case Empty -> out.print(EMPTY_MARK);
        }
    }

    private void printSeparator(final int i, final String expressionTrue, final String expressionFalse) {
        out.print(i % boardSize.size() < boardSize.size() - 1 ? expressionTrue : expressionFalse);
    }
}
