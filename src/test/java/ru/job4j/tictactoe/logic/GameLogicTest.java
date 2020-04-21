package ru.job4j.tictactoe.logic;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.board.Board;
import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.logic.impl.GameLogic;
import ru.job4j.tictactoe.policy.WinPolicy;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static ru.job4j.tictactoe.cell.Mark.X;

public class GameLogicTest {
    private Board board;
    private WinPolicy policy;
    private Logic logic;

    @Before
    public void setUp() {
        board = mock(Board.class);
        policy = mock(WinPolicy.class);
        logic = new GameLogic(policy, board);
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
        when(board.get()).thenReturn(List.of(new Cell(0, 0)));
        assertTrue(logic.isMoveAvailable());
    }

    @Test
    public void whenEmptyCellsNotPresentThanIsMoveAvailableShouldReturnFalse() {
        when(board.get()).thenReturn(List.of(new Cell(0, 0, X)));
        assertFalse(logic.isMoveAvailable());
    }

    @Test
    public void whenResetBoardThanBoardResetShouldBeInvoked() {
        logic.resetBoard();

        verify(board).reset();
    }

    @Test
    public void whenPlayerMoveThanBoardUpdateShouldBeInvoked() {
        Cell cell = new Cell(0, 0, X);
        logic.playerMove(cell);

        verify(board).update(cell);
    }
}