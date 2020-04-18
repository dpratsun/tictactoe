package ru.job4j.tictactoe.policy;

import org.junit.Test;
import ru.job4j.tictactoe.table.GameDataTable;
import ru.job4j.tictactoe.table.GameTable;

import static org.junit.Assert.*;
import static ru.job4j.tictactoe.table.Mark.X;

public class MultipleWinsPolicyTest {
    @Test
    public void whenWinCountShouldBe2ThanAfterOneWinLineIsWinShouldReturnFalse() {
        GameDataTable data = new GameDataTable(GameDataTable.DEFAULT_SIZE);
        MultipleWinsPolicy policy = new MultipleWinsPolicy(data, 2);

        makeWinLine(data);

        assertFalse(policy.isWin(X));
    }

    @Test
    public void whenWinCountShouldBe2ThanAfterTwoWinLinesIsWinShouldReturnTrue() {
        GameDataTable data = new GameDataTable(GameDataTable.DEFAULT_SIZE);
        MultipleWinsPolicy policy = new MultipleWinsPolicy(data, 2);

        makeWinLine(data);

        assertFalse(policy.isWin(X));

        data.reset();
        makeWinLine(data);

        assertTrue(policy.isWin(X));
    }

    private void makeWinLine(GameTable data) {
        data.getCell(0, 0).setMark(X);
        data.getCell(1, 0).setMark(X);
        data.getCell(2, 0).setMark(X);
    }
}