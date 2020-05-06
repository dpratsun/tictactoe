package ru.job4j.tictactoe.logic;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.logic.impl.TicTacToeLogic;
import ru.job4j.tictactoe.policy.impl.OneWinPolicy;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static ru.job4j.tictactoe.logic.Mark.Empty;
import static ru.job4j.tictactoe.logic.Mark.X;

public class TicTacToeLogicTest {
    private Logic logic;

    @Before
    public void setUp() {
        logic = new TicTacToeLogic(new OneWinPolicy(), 2);
    }

    @Test
    public void whenEmptyMarkPresentThanIsEmptyMarkPresentShouldReturnTrue() {
        assertTrue(logic.isEmptyMarkPresent());
    }

    @Test
    public void whenEmptyMarkNotPresentThanIsEmptyMarkPresentShouldReturnFalse() {
        logic.setMark(0, 0, Mark.X);
        logic.setMark(0, 1, Mark.X);
        logic.setMark(1, 0, Mark.X);
        logic.setMark(1, 1, Mark.X);

        assertFalse(logic.isEmptyMarkPresent());
    }

    @Test
    public void whenWinLinePresentThanIsWinShouldReturnTrue() {
        logic.setMark(0, 0, Mark.X);
        logic.setMark(0, 1, Mark.X);

        assertTrue(logic.isWin(Mark.X));
    }

    @Test
    public void whenWinLineNotPresentThanIsWinShouldReturnFalse() {
        assertFalse(logic.isWin(Mark.X));
    }

    @Test
    public void whenSetMarkThanMarkShouldBeChanged() {
        assertThat(logic.getMark(0, 0), is(Empty));
        logic.setMark(0, 0, X);
        assertThat(logic.getMark(0, 0), is(X));
    }

    @Test
    public void whenGetBoardSizeThanSizeShouldBeReturned() {
        assertThat(logic.getBoardSize(), is(2));
    }

    @Test
    public void whenResetGameBoardThanPreviousMarksShouldBeEmpty() {
        logic.setMark(0, 0, Mark.X);
        logic.resetGameBoard(1);

        assertThat(logic.getMark(0, 0), is(Empty));
    }

    @Test
    public void whenResetGameBoardThanSizeShouldBeChanged() {
        assertThat(logic.getBoardSize(), is(2));

        logic.resetGameBoard(1);

        assertThat(logic.getBoardSize(), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMarkNotEmptyThanSetMarkShouldThrowException() {
        logic.setMark(0, 0, X);
        logic.setMark(0, 0, X);
    }

}