package ru.job4j.tictactoe.policy;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.policy.impl.OneWinPolicy;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.job4j.tictactoe.logic.Mark.Empty;
import static ru.job4j.tictactoe.logic.Mark.X;

public class OneWinPolicyTest {
    private Logic logic;
    private WinPolicy policy;

    @Before
    public void setUp() {
        logic = mock(Logic.class);
        when(logic.getBoardSize()).thenReturn(2);
        policy = new OneWinPolicy();
    }

    @Test
    public void whenTableIsEmptyThanIsWinReturnFalse() {
        when(logic.getMark(anyInt(), anyInt())).thenReturn(Empty);

        assertThat(policy.isWin(X, logic), is(false));
    }

    @Test
    public void whenTableHaveMarkedCellsButHaveNoLinesThanIsWinReturnFalse() {
        when(logic.getMark(anyInt(), anyInt())).thenReturn(Empty);
        when(logic.getMark(0, 0)).thenReturn(X);

        assertThat(policy.isWin(X, logic), is(false));
    }

    @Test
    public void whenHorizontalLineMarkedBySameMarksThanIsWinReturnTrue() {
        when(logic.getMark(anyInt(), anyInt())).thenReturn(Empty);
        when(logic.getMark(0, 0)).thenReturn(X);
        when(logic.getMark(0, 1)).thenReturn(X);

        assertThat(policy.isWin(X, logic), is(true));
    }

    @Test
    public void whenVerticalLineMarkedBySameMarksThanIsWinReturnTrue() {
        when(logic.getMark(anyInt(), anyInt())).thenReturn(Empty);
        when(logic.getMark(0, 0)).thenReturn(X);
        when(logic.getMark(1, 0)).thenReturn(X);

        assertThat(policy.isWin(X, logic), is(true));
    }

    @Test
    public void whenDiagonalMarkedBySameMarksThanIsWinReturnTrue() {
        when(logic.getMark(anyInt(), anyInt())).thenReturn(Empty);
        when(logic.getMark(0, 0)).thenReturn(X);
        when(logic.getMark(1, 1)).thenReturn(X);

        assertThat(policy.isWin(X, logic), is(true));
    }

    @Test
    public void whenOtherDiagonalMarkedBySameMarksThanIsWinReturnTrue() {
        when(logic.getMark(anyInt(), anyInt())).thenReturn(Empty);
        when(logic.getMark(0, 1)).thenReturn(X);
        when(logic.getMark(1, 0)).thenReturn(X);

        assertThat(policy.isWin(X, logic), is(true));
    }
}