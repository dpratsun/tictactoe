package ru.job4j.tictactoe.policy;

import org.junit.Test;
import ru.job4j.tictactoe.board.impl.GameBoard;
import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.cell.Mark;
import ru.job4j.tictactoe.cell.impl.MemoryCellStorage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ru.job4j.tictactoe.cell.Mark.X;

public class MultipleWinsPolicyIntegrationTest {
    private final static int TEST_BOARD_SIZE = 2;
    private final static int TEST_COUNT_TO_WIN = 2;
    private final static Mark TEST_WIN_MARK = X;

    @Test
    public void whenWinCountShouldBe2ThanAfterTwoWinLinesIsWinShouldReturnTrue() {
        var storage = new MemoryCellStorage();
        var board = new GameBoard(storage, TEST_BOARD_SIZE);
        var oneWinPolicy = new OneWinPolicy(board, board);
        var policy = new MultipleWinsPolicy(oneWinPolicy, TEST_COUNT_TO_WIN);

        storage.add(new Cell(0, 0, TEST_WIN_MARK));
        storage.add(new Cell(0, 1, TEST_WIN_MARK));
        storage.add(new Cell(1, 0));
        storage.add(new Cell(1, 1));

        assertFalse(policy.isWin(TEST_WIN_MARK));
        assertTrue(policy.isWin(TEST_WIN_MARK));
    }
}