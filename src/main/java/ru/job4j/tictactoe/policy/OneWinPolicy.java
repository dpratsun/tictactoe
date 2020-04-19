package ru.job4j.tictactoe.policy;

import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.cell.CellStorage;
import ru.job4j.tictactoe.cell.Mark;

public class OneWinPolicy implements WinPolicy {
    private final CellStorage storage;
    private final int boardSize;

    public OneWinPolicy(CellStorage storage, int boardSize) {
        this.storage = storage;
        this.boardSize = boardSize;
    }

    @Override
    public boolean isWin(Mark mark) {
        boolean result = false;
        int diagonalCnt = 0;
        int mirrorDiagonalCnt = 0;

        for (int x = 0; x < boardSize; x++) {
            mirrorDiagonalCnt += mark.equals(getCellMark((boardSize - x - 1), x)) ? 1 : 0;
            if (mirrorDiagonalCnt == boardSize) {
                result = true;
                break;
            }
            if (mark.equals(getCellMark(x, x))) {
                if (++diagonalCnt == boardSize || isRowOrColumnFilledByMark(mark, x)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    private boolean isRowOrColumnFilledByMark(Mark mark, final int x) {
        var rowCnt = 0;
        var colCnt = 0;

        for (int y = 0; y < boardSize; y++) {
            rowCnt += mark.equals(getCellMark(x, y)) ? 1 : 0;
            colCnt += mark.equals(getCellMark(y, x)) ? 1 : 0;
        }

        return rowCnt == boardSize || colCnt == boardSize;
    }

    private Mark getCellMark(int x, int y) {
        return storage.find(new Cell(x, y)).getMark();
    }
}
