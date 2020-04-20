package ru.job4j.tictactoe.state;

import org.junit.Test;
import ru.job4j.tictactoe.messages.MessagePrinter;
import ru.job4j.tictactoe.state.impl.InitializeState;
import ru.job4j.tictactoe.state.impl.StartState;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;
import static ru.job4j.tictactoe.messages.Messages.START_MESSAGE;

public class StartStateTest {
    @Test
    public void invocationOfPerformMethod() {
        StateStorage storage = mock(StateStorage.class);
        when(storage.get(InitializeState.class.getName())).thenReturn(mock(State.class));
        MessagePrinter printer = mock(MessagePrinter.class);
        StateContext context = mock(StateContext.class);

        new StartState(storage, printer, 2).perform(context);

        verify(printer).print(START_MESSAGE, "2");
        verify(storage).get(InitializeState.class.getName());
        verify(context).setNext(any(State.class));
    }

    @Test
    public void invocationOfIsEndStateShouldReturnFalse() {
        assertFalse(
                new StartState(
                        mock(StateStorage.class),
                        mock(MessagePrinter.class),
                        2
                ).isEndState()
        );
    }
}