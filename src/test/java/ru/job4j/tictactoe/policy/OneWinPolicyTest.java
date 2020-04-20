package ru.job4j.tictactoe.policy;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.cell.CellStorage;
import ru.job4j.tictactoe.policy.impl.OneWinPolicy;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.job4j.tictactoe.cell.Mark.X;

public class OneWinPolicyTest {
    private CellStorage storage;
    private WinPolicy policy;

    @Before
    public void setUp() {
        storage = mock(CellStorage.class);
        policy = new OneWinPolicy(storage, 2);
    }

    @Test
    public void whenTableIsEmptyThanIsWinReturnFalse() {
        when(storage.find(any(Cell.class))).thenReturn(new Cell(0, 0));

        assertThat(policy.isWin(X), is(false));
    }

    @Test
    public void whenTableHaveMarkedCellsButHaveNoLinesThanIsWinReturnFalse() {
        when(storage.find(new Cell(0, 0))).thenReturn(new Cell(0, 0, X));
        when(storage.find(any(Cell.class))).thenReturn(new Cell(0, 0));

        assertThat(policy.isWin(X), is(false));
    }

    @Test
    public void whenHorizontalLineMarkedBySameMarksThanIsWinReturnTrue() {
        when(storage.find(any(Cell.class))).thenReturn(new Cell(0, 0));
        when(storage.find(new Cell(0, 0))).thenReturn(new Cell(0, 0, X));
        when(storage.find(new Cell(0, 1))).thenReturn(new Cell(0, 1, X));

        assertThat(policy.isWin(X), is(true));
    }

    @Test
    public void whenVerticalLineMarkedBySameMarksThanIsWinReturnTrue() {
        when(storage.find(any(Cell.class))).thenReturn(new Cell(0, 0));
        when(storage.find(new Cell(0, 0))).thenReturn(new Cell(0, 0, X));
        when(storage.find(new Cell(1, 0))).thenReturn(new Cell(1, 0, X));

        assertThat(policy.isWin(X), is(true));
    }

    @Test
    public void whenDiagonalMarkedBySameMarksThanIsWinReturnTrue() {
        when(storage.find(any(Cell.class))).thenReturn(new Cell(0, 0));
        when(storage.find(new Cell(0, 0))).thenReturn(new Cell(0, 0, X));
        when(storage.find(new Cell(1, 1))).thenReturn(new Cell(1, 1, X));

        assertThat(policy.isWin(X), is(true));
    }

    @Test
    public void whenOtherDiagonalMarkedBySameMarksThanIsWinReturnTrue() {
        when(storage.find(any(Cell.class))).thenReturn(new Cell(0, 0));
        when(storage.find(new Cell(0, 1))).thenReturn(new Cell(0, 1, X));
        when(storage.find(new Cell(1, 0))).thenReturn(new Cell(1, 0, X));

        assertThat(policy.isWin(X), is(true));
    }
}