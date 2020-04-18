package ru.job4j.tictactoe.policy;

import ru.job4j.tictactoe.table.GameTable;
import ru.job4j.tictactoe.table.Mark;

import static ru.job4j.tictactoe.table.Mark.O;
import static ru.job4j.tictactoe.table.Mark.X;

import java.util.HashMap;
import java.util.Map;

public class MultipleWinsPolicy extends OneWinPolicy {
    public final static int DEFAULT_COUNT_TO_WIN = 1;
    private final Map<Mark, Integer> counter = new HashMap<>(Map.of(X, 0, O, 0));
    private final int winsToWin;

    public MultipleWinsPolicy(GameTable table, int winsToWin) {
        super(table);
        this.winsToWin = winsToWin;
    }

    @Override
    public boolean isWin(Mark mark) {
        return super.isWin(mark) && checkWin(mark);
    }

    private boolean checkWin(Mark mark) {
        counter.put(mark, counter.get(mark) + 1);
        return winsToWin == counter.get(mark);
    }
}
