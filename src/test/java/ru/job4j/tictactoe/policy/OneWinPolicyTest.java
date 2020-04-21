package ru.job4j.tictactoe.policy;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.board.BoardCell;
import ru.job4j.tictactoe.board.BoardSize;
import ru.job4j.tictactoe.cell.Cell;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.job4j.tictactoe.cell.Mark.X;

public class OneWinPolicyTest {
    private BoardCell cellProvider;
    private BoardSize sizeProvider;
    private WinPolicy policy;

    @Before
    public void setUp() {
        cellProvider = mock(BoardCell.class);
        sizeProvider = mock(BoardSize.class);
        when(sizeProvider.size()).thenReturn(2);
        policy = new OneWinPolicy(cellProvider, sizeProvider);
    }

    @Test
    public void whenTableIsEmptyThanIsWinReturnFalse() {
        when(cellProvider.get(anyInt(), anyInt())).thenReturn(new Cell(0, 0));

        assertThat(policy.isWin(X), is(false));
    }

    @Test
    public void whenTableHaveMarkedCellsButHaveNoLinesThanIsWinReturnFalse() {
        when(cellProvider.get(0, 0)).thenReturn(new Cell(0, 0, X));
        when(cellProvider.get(anyInt(), anyInt())).thenReturn(new Cell(0, 0));

        assertThat(policy.isWin(X), is(false));
    }

    @Test
    public void whenHorizontalLineMarkedBySameMarksThanIsWinReturnTrue() {
        when(cellProvider.get(anyInt(), anyInt())).thenReturn(new Cell(0, 0));
        when(cellProvider.get(0, 0)).thenReturn(new Cell(0, 0, X));
        when(cellProvider.get(0, 1)).thenReturn(new Cell(0, 1, X));

        assertThat(policy.isWin(X), is(true));
    }

    @Test
    public void whenVerticalLineMarkedBySameMarksThanIsWinReturnTrue() {
        when(cellProvider.get(anyInt(), anyInt())).thenReturn(new Cell(0, 0));
        when(cellProvider.get(0, 0)).thenReturn(new Cell(0, 0, X));
        when(cellProvider.get(0, 1)).thenReturn(new Cell(1, 0, X));

        assertThat(policy.isWin(X), is(true));
    }

    @Test
    public void whenDiagonalMarkedBySameMarksThanIsWinReturnTrue() {
        when(cellProvider.get(anyInt(), anyInt())).thenReturn(new Cell(0, 0));
        when(cellProvider.get(0, 0)).thenReturn(new Cell(0, 0, X));
        when(cellProvider.get(1, 1)).thenReturn(new Cell(1, 1, X));

        assertThat(policy.isWin(X), is(true));
    }

    @Test
    public void whenOtherDiagonalMarkedBySameMarksThanIsWinReturnTrue() {
        when(cellProvider.get(anyInt(), anyInt())).thenReturn(new Cell(0, 0));
        when(cellProvider.get(0, 1)).thenReturn(new Cell(0, 1, X));
        when(cellProvider.get(1, 0)).thenReturn(new Cell(1, 0, X));

        assertThat(policy.isWin(X), is(true));
    }
}