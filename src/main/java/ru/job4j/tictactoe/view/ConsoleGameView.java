package ru.job4j.tictactoe.view;

import ru.job4j.tictactoe.table.Cell;
import ru.job4j.tictactoe.table.GameTable;

import java.util.function.Predicate;

import static java.lang.System.*;

public class ConsoleGameView implements GameView {
    public final static String O_MARK = " O ";
    public final static String X_MARK = " X ";
    public final static String EMPTY_MARK = "   ";
    public final static String COL_SEPARATOR = "|";
    public final static String ROW_SEPARATOR = "----";
    private final GameTable table;

    public ConsoleGameView(GameTable table) {
        this.table = table;
    }

    @Override
    public void show() {
        final Predicate<Integer> notLast = i -> i != table.getSize() - 1;
        for (int row = 0; row < table.getSize(); row++) {
            for (int col = 0; col < table.getSize(); col++) {
                printCell(table.getCell(row, col));
                out.print(notLast.test(col) ? COL_SEPARATOR : lineSeparator());
            }
            if (notLast.test(row)) {
                out.println(ROW_SEPARATOR.repeat(table.getSize()));
            }
        }
    }

    private void printCell(final Cell cell) {
        switch (cell.getMark()) {
            case O -> out.print(O_MARK);
            case X -> out.print(X_MARK);
            case Empty -> out.print(EMPTY_MARK);
        }
    }
}
