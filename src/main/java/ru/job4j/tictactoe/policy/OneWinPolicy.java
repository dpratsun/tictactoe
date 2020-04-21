package ru.job4j.tictactoe.policy;

import ru.job4j.tictactoe.board.BoardCell;
import ru.job4j.tictactoe.board.BoardSize;
import ru.job4j.tictactoe.cell.Mark;

public class OneWinPolicy implements WinPolicy {
    private final BoardCell cellProvider;
    private final BoardSize sizeProvider;

    public OneWinPolicy(BoardCell cellProvider, BoardSize sizeProvider) {
        this.cellProvider = cellProvider;
        this.sizeProvider = sizeProvider;
    }

    @Override
    public boolean isWin(Mark mark) {
        boolean result = false;
        int diagonalCnt = 0;
        int mirrorDiagonalCnt = 0;

        for (int x = 0; x < sizeProvider.size(); x++) {
            mirrorDiagonalCnt += mark.equals(getCellMark((sizeProvider.size() - x - 1), x)) ? 1 : 0;
            if (mirrorDiagonalCnt == sizeProvider.size()) {
                result = true;
                break;
            }
            if (mark.equals(getCellMark(x, x))) {
                if (++diagonalCnt == sizeProvider.size() || isRowOrColumnFilledByMark(mark, x)) {
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

        for (int y = 0; y < sizeProvider.size(); y++) {
            rowCnt += mark.equals(getCellMark(x, y)) ? 1 : 0;
            colCnt += mark.equals(getCellMark(y, x)) ? 1 : 0;
        }

        return rowCnt == sizeProvider.size() || colCnt == sizeProvider.size();
    }

    private Mark getCellMark(int x, int y) {
        return cellProvider.get(x, y).getMark();
    }
}
