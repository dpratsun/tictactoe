package ru.job4j.tictactoe.policy;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.policy.impl.MultipleWinsPolicy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.job4j.tictactoe.cell.Mark.X;

public class MultipleWinsPolicyTest {
    private WinPolicy oneWinPolicy;
    private WinPolicy policy;

    @Before
    public void setUp() {
        oneWinPolicy = mock(WinPolicy.class);
        policy = new MultipleWinsPolicy(oneWinPolicy, 2);
    }

    @Test
    public void whenWinCountShouldBe2ThanAfterOneWinLineIsWinShouldReturnFalse() {
        when(oneWinPolicy.isWin(X)).thenReturn(true);
        assertFalse(policy.isWin(X));
    }

    @Test
    public void whenWinCountShouldBe2ThanAfterTwoWinLinesIsWinShouldReturnTrue() {
        when(oneWinPolicy.isWin(X)).thenReturn(true, true);
        assertFalse(policy.isWin(X));
        assertTrue(policy.isWin(X));
    }
}