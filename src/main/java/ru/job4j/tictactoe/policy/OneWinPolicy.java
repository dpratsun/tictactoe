package ru.job4j.tictactoe.policy;

import ru.job4j.tictactoe.table.Cell;
import ru.job4j.tictactoe.table.GameTable;
import ru.job4j.tictactoe.table.Mark;

import java.util.function.Predicate;

public class OneWinPolicy implements WinPolicy {
    private final GameTable table;

    public OneWinPolicy(GameTable table) {
        this.table = table;
    }

    @Override
    public boolean isWin(Mark mark) {
        return checkWin(c -> mark.equals(c.getMark()));
    }

    private boolean checkWin(final Predicate<Cell> predicate) {
        boolean result = false;
        int diagonalCnt = 0;
        int mirrorDiagonalCnt = 0;
        final int tableSize = table.getSize();

        for (int row = 0; row < tableSize; row++) {
            mirrorDiagonalCnt += predicate.test(table.getCell(tableSize - row - 1, row)) ? 1 : 0;
            if (mirrorDiagonalCnt == tableSize) {
                result = true;
                break;
            }
            if (predicate.test(table.getCell(row, row))) {
                if (++diagonalCnt == tableSize || isRowOrColumnFilledByMark(predicate, row)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    private boolean isRowOrColumnFilledByMark(final Predicate<Cell> predicate, final int row) {
        var rowCnt = 0;
        var colCnt = 0;

        for (int col = 0; col < table.getSize(); col++) {
            rowCnt += predicate.test(table.getCell(row, col)) ? 1 : 0;
            colCnt += predicate.test(table.getCell(col, row)) ? 1 : 0;
        }

        return rowCnt == table.getSize() || colCnt == table.getSize();
    }
}
