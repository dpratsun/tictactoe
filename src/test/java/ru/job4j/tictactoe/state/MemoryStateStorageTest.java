package ru.job4j.tictactoe.state;

import org.junit.Test;
import ru.job4j.tictactoe.board.BoardSize;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.message.MessagePrinter;
import ru.job4j.tictactoe.player.storage.PlayerStorage;
import ru.job4j.tictactoe.state.impl.MemoryStateStorage;
import ru.job4j.tictactoe.state.impl.StartState;
import ru.job4j.tictactoe.view.View;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class MemoryStateStorageTest {
    @Test
    public void whenGetThanCorrectStateShouldBeReturned() {
        var printer = mock(MessagePrinter.class);
        var boardSize = mock(BoardSize.class);
        MemoryStateStorage storage = new MemoryStateStorage(
                printer,
                boardSize,
                mock(Logic.class),
                mock(PlayerStorage.class),
                mock(View.class)
        );

        assertThat(
                storage.get(StartState.class.getName()),
                is(new StartState(storage, printer, boardSize))
        );
    }
}