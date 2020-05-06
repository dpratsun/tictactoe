package ru.job4j.tictactoe.policy.impl;

import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.logic.Mark;
import ru.job4j.tictactoe.policy.WinPolicy;

public class OneWinPolicy implements WinPolicy {

    @Override
    public boolean isWin(Mark mark, Logic logic) {
        boolean result = false;
        int diagonalCnt = 0;
        int mirrorDiagonalCnt = 0;

        for (int x = 0; x < logic.getBoardSize(); x++) {
            mirrorDiagonalCnt += mark.equals(logic.getMark(logic.getBoardSize() - x - 1, x)) ? 1 : 0;
            if (mirrorDiagonalCnt == logic.getBoardSize()) {
                result = true;
                break;
            }
            if (mark.equals(logic.getMark(x, x))) {
                if (++diagonalCnt == logic.getBoardSize() || isRowOrColumnFilledByMark(logic, mark, x)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    private boolean isRowOrColumnFilledByMark(Logic logic, Mark mark, final int x) {
        var rowCnt = 0;
        var colCnt = 0;

        for (int y = 0; y < logic.getBoardSize(); y++) {
            rowCnt += mark.equals(logic.getMark(x, y)) ? 1 : 0;
            colCnt += mark.equals(logic.getMark(y, x)) ? 1 : 0;
        }

        return rowCnt == logic.getBoardSize() || colCnt == logic.getBoardSize();
    }
}
