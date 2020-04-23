package ru.job4j.tictactoe.policy.impl;

import ru.job4j.tictactoe.board.BoardCell;
import ru.job4j.tictactoe.board.BoardSize;
import ru.job4j.tictactoe.cell.Mark;
import ru.job4j.tictactoe.policy.WinPolicy;

public class OneWinPolicy implements WinPolicy {
    private final BoardCell boardCell;
    private final BoardSize boardSize;

    public OneWinPolicy(BoardCell boardCell, BoardSize boardSize) {
        this.boardCell = boardCell;
        this.boardSize = boardSize;
    }

    @Override
    public boolean isWin(Mark mark) {
        boolean result = false;
        int diagonalCnt = 0;
        int mirrorDiagonalCnt = 0;

        for (int x = 0; x < boardSize.size(); x++) {
            mirrorDiagonalCnt += mark.equals(getCellMark((boardSize.size() - x - 1), x)) ? 1 : 0;
            if (mirrorDiagonalCnt == boardSize.size()) {
                result = true;
                break;
            }
            if (mark.equals(getCellMark(x, x))) {
                if (++diagonalCnt == boardSize.size() || isRowOrColumnFilledByMark(mark, x)) {
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

        for (int y = 0; y < boardSize.size(); y++) {
            rowCnt += mark.equals(getCellMark(x, y)) ? 1 : 0;
            colCnt += mark.equals(getCellMark(y, x)) ? 1 : 0;
        }

        return rowCnt == boardSize.size() || colCnt == boardSize.size();
    }

    private Mark getCellMark(int x, int y) {
        return boardCell.get(x, y).getMark();
    }
}
