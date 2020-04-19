package ru.job4j.tictactoe.view.impl;

import ru.job4j.tictactoe.cell.CellStorage;
import ru.job4j.tictactoe.view.View;
import ru.job4j.tictactoe.cell.Cell;

import java.util.List;

import static java.lang.System.*;

public class ConsoleBoardView implements View {
    private final static String O_MARK = " O ";
    private final static String X_MARK = " X ";
    private final static String EMPTY_MARK = "   ";
    private final static String COL_SEPARATOR = "|";
    private final static String ROW_SEPARATOR = "----";
    private final static String LS = lineSeparator();

    private final CellStorage storage;
    private final int size;

    public ConsoleBoardView(final int size, final CellStorage storage) {
        this.size = size;
        this.storage = storage;
    }

    @Override
    public void show() {
        List<Cell> cells = storage.findAll();
        for (int i = 0; i < cells.size(); i++) {
            printCell(cells.get(i));
            printSeparator(i, COL_SEPARATOR, LS);
            if (i < cells.size() - 1) {
                printSeparator(i, "", ROW_SEPARATOR.repeat(size) + LS);
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
        out.print(i % size < size - 1 ? expressionTrue : expressionFalse);
    }
}
