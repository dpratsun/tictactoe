package ru.job4j.tictactoe.state;

import org.junit.Test;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.messages.MessagePrinter;
import ru.job4j.tictactoe.state.impl.InitializeState;
import ru.job4j.tictactoe.state.impl.PlayerMoveState;
import ru.job4j.tictactoe.state.impl.StartState;
import ru.job4j.tictactoe.view.View;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InitializeStateTest {
    @Test
    public void invocationOfPerformMethod() {
        StateStorage storage = mock(StateStorage.class);
        when(storage.get(any())).thenReturn(mock(State.class));
        Logic logic = mock(Logic.class);
        View view = mock(View.class);
        StateContext context = mock(StateContext.class);

        new InitializeState(storage, logic, view).perform(context);

        verify(logic).resetBoard();
        verify(view).show();
        verify(storage).get(PlayerMoveState.class.getName());
        verify(context).setNext(any(State.class));
    }

    @Test
    public void invocationOfIsEndStateShouldReturnFalse() {
        assertFalse(
                new InitializeState(
                        mock(StateStorage.class),
                        mock(Logic.class),
                        mock(View.class)
                ).isEndState()
        );
    }
}