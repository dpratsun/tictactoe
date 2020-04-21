package ru.job4j.tictactoe.game;

import org.junit.Test;
import ru.job4j.tictactoe.game.impl.TicTacToeGame;
import ru.job4j.tictactoe.state.State;

import static org.mockito.Mockito.*;

public class TicTacToeGameTest {

    @Test
    public void whenStateIsEndStateThanGameLoopShouldNotStart() {
        TicTacToeGame game = new TicTacToeGame();
        State state = mock(State.class);
        when(state.isEndState()).thenReturn(true);
        game.setCurrentState(state);

        game.start();

        verify(state, never()).perform(game);
    }
}