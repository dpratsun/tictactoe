package ru.job4j.tictactoe.state;

import org.junit.Test;
import ru.job4j.tictactoe.board.BoardSize;
import ru.job4j.tictactoe.message.MessagePrinter;
import ru.job4j.tictactoe.state.impl.InitializeState;
import ru.job4j.tictactoe.state.impl.StartState;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;
import static ru.job4j.tictactoe.message.Message.START_MESSAGE;

public class StartStateTest {
    private final static int TEST_BOARD_SIZE = 2;
    @Test
    public void invocationOfPerformMethod() {
        StateStorage storage = mock(StateStorage.class);
        when(storage.get(InitializeState.class.getName())).thenReturn(mock(State.class));
        MessagePrinter printer = mock(MessagePrinter.class);
        StateContext context = mock(StateContext.class);
        BoardSize boardSize = mock(BoardSize.class);
        when(boardSize.size()).thenReturn(TEST_BOARD_SIZE);

        new StartState(storage, printer, boardSize).perform(context);

        verify(printer).print(START_MESSAGE, String.valueOf(TEST_BOARD_SIZE));
        verify(storage).get(InitializeState.class.getName());
        verify(context).setCurrentState(any(State.class));
    }

    @Test
    public void invocationOfIsEndStateShouldReturnFalse() {
        assertFalse(
                new StartState(
                        mock(StateStorage.class),
                        mock(MessagePrinter.class),
                        mock(BoardSize.class)
                ).isEndState()
        );
    }
}