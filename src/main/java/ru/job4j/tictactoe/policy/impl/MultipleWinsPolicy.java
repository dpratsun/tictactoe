package ru.job4j.tictactoe.policy.impl;

import ru.job4j.tictactoe.cell.Mark;
import ru.job4j.tictactoe.policy.WinPolicy;

import java.util.HashMap;
import java.util.Map;

import static ru.job4j.tictactoe.cell.Mark.O;
import static ru.job4j.tictactoe.cell.Mark.X;

public class MultipleWinsPolicy implements WinPolicy {
    public final static int DEFAULT_COUNT_TO_WIN = 1;
    private final Map<Mark, Integer> counter = new HashMap<>(Map.of(X, 0, O, 0));
    private final int winsToWin;
    private final WinPolicy policy;

    public MultipleWinsPolicy(WinPolicy policy, int winsToWin) {
        this.policy = policy;
        this.winsToWin = winsToWin;
    }

    @Override
    public boolean isWin(Mark mark) {
        if (policy.isWin(mark)) {
            incrementWinCounter(mark);
        }
        return winsToWin == counter.get(mark);
    }

    private void incrementWinCounter(Mark mark) {
        counter.put(mark, counter.get(mark) + 1);
    }
}
