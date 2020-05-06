package ru.job4j.tictactoe.policy;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.policy.impl.MultipleWinsPolicy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.job4j.tictactoe.logic.Mark.X;

public class MultipleWinsPolicyTest {
    private WinPolicy oneWinPolicy;
    private WinPolicy policy;
    private Logic logic;

    @Before
    public void setUp() {
        oneWinPolicy = mock(WinPolicy.class);
        policy = new MultipleWinsPolicy(oneWinPolicy, 2);
        logic = mock(Logic.class);
    }

    @Test
    public void whenWinCountShouldBe2ThanAfterOneWinLineIsWinShouldReturnFalse() {
        when(oneWinPolicy.isWin(X, logic)).thenReturn(true);
        assertFalse(policy.isWin(X, logic));
    }

    @Test
    public void whenWinCountShouldBe2ThanAfterTwoWinLinesIsWinShouldReturnTrue() {
        when(oneWinPolicy.isWin(X, logic)).thenReturn(true, true);
        assertFalse(policy.isWin(X, logic));
        assertTrue(policy.isWin(X, logic));
    }
}