package ru.job4j.tictactoe.state;

import org.junit.Test;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.messages.MessagePrinter;
import ru.job4j.tictactoe.player.provider.PlayerProvider;
import ru.job4j.tictactoe.state.impl.MemoryStateStorage;
import ru.job4j.tictactoe.state.impl.StartState;
import ru.job4j.tictactoe.view.View;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static ru.job4j.tictactoe.logic.impl.GameLogic.DEFAULT_BOARD_SIZE;

public class MemoryStateStorageTest {
    @Test
    public void whenGetThanCorrectStateShouldBeReturned() {
        MessagePrinter printer = mock(MessagePrinter.class);
        MemoryStateStorage storage = new MemoryStateStorage(
                printer,
                mock(Logic.class),
                mock(PlayerProvider.class),
                mock(View.class),
                DEFAULT_BOARD_SIZE
        );

        assertThat(
                storage.get(StartState.class.getName()),
                is(new StartState(storage, printer, DEFAULT_BOARD_SIZE))
        );
    }
}