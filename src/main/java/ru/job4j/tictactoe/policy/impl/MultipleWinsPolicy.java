package ru.job4j.tictactoe.policy.impl;

import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.logic.Mark;
import ru.job4j.tictactoe.policy.WinPolicy;

import java.util.HashMap;
import java.util.Map;

import static ru.job4j.tictactoe.logic.Mark.O;
import static ru.job4j.tictactoe.logic.Mark.X;

public class MultipleWinsPolicy implements WinPolicy {
    public final static int DEFAULT_COUNT_TO_WIN = 1;
    private final Map<Mark, Integer> counter = new HashMap<>(2);
    private final int winsToWin;
    private final WinPolicy policy;

    public MultipleWinsPolicy(WinPolicy policy, int winsToWin) {
        this.policy = policy;
        this.winsToWin = winsToWin;
        counter.put(X, 0);
        counter.put(O, 0);
    }

    @Override
    public boolean isWin(Mark mark, Logic logic) {
        if (policy.isWin(mark, logic)) {
            incrementWinCounter(mark);
        }
        return winsToWin == counter.get(mark);
    }

    private void incrementWinCounter(Mark mark) {
        counter.put(mark, counter.get(mark) + 1);
    }
}
