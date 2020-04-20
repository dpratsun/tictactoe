package ru.job4j.tictactoe.logic;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.cell.CellStorage;
import ru.job4j.tictactoe.logic.impl.GameLogic;
import ru.job4j.tictactoe.policy.WinPolicy;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.job4j.tictactoe.cell.Mark.Empty;
import static ru.job4j.tictactoe.cell.Mark.X;

public class GameLogicTest {
    private CellStorage storage;
    private WinPolicy policy;
    private Logic logic;

    @Before
    public void setUp() {
        storage = mock(CellStorage.class);
        policy = mock(WinPolicy.class);
        logic = new GameLogic(storage, policy, 2);
    }

    @Test
    public void whenLogicCreatedThanCertainAmountOfCellsShouldBeAddedToStorage() {
        verify(storage, times(2 * 2)).add(any(Cell.class));
    }

    @Test
    public void whenXNotWinThanIsWinShouldReturnFalse() {
        when(policy.isWin(X)).thenReturn(false);
        assertFalse(logic.isWin(X));
    }

    @Test
    public void whenXWinThanIsWinShouldReturnTrue() {
        when(policy.isWin(X)).thenReturn(true);
        assertTrue(logic.isWin(X));
    }

    @Test
    public void whenEmptyCellsPresentThanIsMoveAvailableShouldReturnTrue() {
        when(storage.findAll()).thenReturn(List.of(new Cell(0, 0)));
        assertTrue(logic.isMoveAvailable());
    }

    @Test
    public void whenEmptyCellsNotPresentThanIsMoveAvailableShouldReturnFalse() {
        when(storage.findAll()).thenReturn(List.of(new Cell(0, 0, X)));
        assertFalse(logic.isMoveAvailable());
    }

    @Test
    public void whenResetBoardThanCellMarkShouldBeEmpty() {
        Cell cell = new Cell(0 , 0 , X);
        when(storage.findAll()).thenReturn(List.of(cell));

        assertThat(cell.getMark(), is(X));

        logic.resetBoard();

        assertThat(cell.getMark(), is(Empty));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCellAlreadyMarkedThanPlayerMoveThrowsException() {
        Cell cell = new Cell(0 , 0 , X);
        when(storage.find(any(Cell.class))).thenReturn(cell);

        logic.playerMove(cell);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCellNotFoundThanPlayerMoveThrowsException() {
        doThrow(new IndexOutOfBoundsException("Error")).when(storage).find(any(Cell.class));
        logic.playerMove(new Cell(0, 0));
    }

    @Test
    public void whenCellEmptyThanCellMarkShouldChange() {
        Cell empty = new Cell(0 , 0);
        when(storage.find(any(Cell.class))).thenReturn(empty);

        logic.playerMove(new Cell(0, 0, X));

        assertThat(empty.getMark(), is(X));
    }
}