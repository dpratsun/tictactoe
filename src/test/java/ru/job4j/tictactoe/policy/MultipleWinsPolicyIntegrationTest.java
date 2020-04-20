package ru.job4j.tictactoe.policy;

import org.junit.Test;
import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.cell.impl.MemoryCellStorage;
import ru.job4j.tictactoe.policy.impl.MultipleWinsPolicy;
import ru.job4j.tictactoe.policy.impl.OneWinPolicy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ru.job4j.tictactoe.cell.Mark.X;

public class MultipleWinsPolicyIntegrationTest {

    @Test
    public void whenWinCountShouldBe2ThanAfterTwoWinLinesIsWinShouldReturnTrue() {
        var storage = new MemoryCellStorage();
        var oneWinPolicy = new OneWinPolicy(storage, 2);
        var policy = new MultipleWinsPolicy(oneWinPolicy, 2);

        storage.add(new Cell(0, 0, X));
        storage.add(new Cell(0, 1, X));
        storage.add(new Cell(1, 0));
        storage.add(new Cell(1, 1));

        assertFalse(policy.isWin(X));
        assertTrue(policy.isWin(X));
    }
}