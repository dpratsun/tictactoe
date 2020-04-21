package ru.job4j.tictactoe.state;

import org.junit.Test;
import ru.job4j.tictactoe.message.MessagePrinter;
import ru.job4j.tictactoe.message.Message;
import ru.job4j.tictactoe.player.Player;
import ru.job4j.tictactoe.player.provider.CurrentPlayerProvider;
import ru.job4j.tictactoe.state.impl.CheckWinState;
import ru.job4j.tictactoe.state.impl.PlayerMoveState;
import ru.job4j.tictactoe.view.View;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class PlayerMoveStateTest {
    @Test
    public void invocationOfPerformMethod() {
        StateStorage storage = mock(StateStorage.class);
        when(storage.get(CheckWinState.class.getName())).thenReturn(mock(State.class));
        CurrentPlayerProvider provider = mock(CurrentPlayerProvider.class);
        Player player = mock(Player.class);
        when(player.getName()).thenReturn("Dmitry");
        when(provider.get()).thenReturn(player);
        MessagePrinter printer = mock(MessagePrinter.class);
        View view = mock(View.class);
        StateContext context = mock(StateContext.class);

        new PlayerMoveState(storage, provider, printer, view).perform(context);

        verify(printer).print(Message.PLAYER_MAKE_MOVE_MESSAGE, "Dmitry");
        verify(player).makeMove();
        verify(printer).print(Message.PLAYER_MOVE_PERFORMED_MESSAGE, "Dmitry");
        verify(view).show();
        verify(storage).get(CheckWinState.class.getName());
        verify(context).setCurrentState(any(State.class));
    }

    @Test
    public void invocationOfIsEndStateShouldReturnFalse() {
        assertFalse(
                new PlayerMoveState(
                        mock(StateStorage.class),
                        mock(CurrentPlayerProvider.class),
                        mock(MessagePrinter.class),
                        mock(View.class)
                ).isEndState()
        );
    }
}