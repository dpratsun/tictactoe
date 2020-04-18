package ru.job4j.tictactoe.policy;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.table.GameDataTable;
import ru.job4j.tictactoe.table.GameTable;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.job4j.tictactoe.table.GameDataTable.DEFAULT_SIZE;
import static ru.job4j.tictactoe.table.Mark.X;

public class OneWinPolicyTest {
    private GameTable data;
    private WinPolicy policy;

    @Before
    public void setUp() throws Exception {
        data = new GameDataTable(DEFAULT_SIZE);
        policy = new OneWinPolicy(data);
    }

    @Test
    public void whenTableIsEmptyThanIsWinReturnFalse() {
        assertThat(policy.isWin(X), is(false));
    }

    @Test
    public void whenTableHaveMarkedCellsButHaveNoLinesThanIsWinReturnFalse() {
        data.getCell(0, 0).setMark(X);
        data.getCell(0, 1).setMark(X);
        assertThat(policy.isWin(X), is(false));
    }

    @Test
    public void whenHorizontalLineMarkedBySameMarksThanIsWinReturnTrue() {
        data.getCell(0, 0).setMark(X);
        data.getCell(0, 1).setMark(X);
        data.getCell(0, 2).setMark(X);
        assertThat(policy.isWin(X), is(true));
    }

    @Test
    public void whenVerticalLineMarkedBySameMarksThanIsWinReturnTrue() {
        data.getCell(0, 0).setMark(X);
        data.getCell(1, 0).setMark(X);
        data.getCell(2, 0).setMark(X);
        assertThat(policy.isWin(X), is(true));
    }

    @Test
    public void whenDiagonalMarkedBySameMarksThanIsWinReturnTrue() {
        data.getCell(0, 0).setMark(X);
        data.getCell(1, 1).setMark(X);
        data.getCell(2, 2).setMark(X);
        assertThat(policy.isWin(X), is(true));
    }

    @Test
    public void whenOtherDiagonalMarkedBySameMarksThanIsWinReturnTrue() {
        data.getCell(0, 2).setMark(X);
        data.getCell(1, 1).setMark(X);
        data.getCell(2, 0).setMark(X);
        assertThat(policy.isWin(X), is(true));
    }
}