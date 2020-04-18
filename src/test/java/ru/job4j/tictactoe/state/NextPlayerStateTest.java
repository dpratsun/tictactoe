package ru.job4j.tictactoe.state;

import org.junit.Test;
import ru.job4j.tictactoe.messages.GameMessages;
import ru.job4j.tictactoe.output.ConsoleOutput;
import ru.job4j.tictactoe.player.Player;
import ru.job4j.tictactoe.player.storage.PlayerListStorage;
import ru.job4j.tictactoe.table.Mark;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.job4j.tictactoe.messages.GameMessages.PLAYER_MOVE_STR;
import static ru.job4j.tictactoe.state.NextPlayerState.KEY;
import static ru.job4j.tictactoe.table.Mark.O;
import static ru.job4j.tictactoe.table.Mark.X;

public class NextPlayerStateTest {
    @Test
    public void whenPerformThanPlayerShouldChange() {
        PlayerListStorage storage = new PlayerListStorage();

        Player first = mock(Player.class);
        when(first.getName()).thenReturn("First");
        when(first.getMark()).thenReturn(X);
        storage.add(first);

        Player second = mock(Player.class);
        when(second.getName()).thenReturn("Second");
        when(second.getMark()).thenReturn(O);
        storage.add(second);

        StateTransitions transitions = new StateTransitions();
        transitions.add(KEY, () -> null);
        StringBuilder sb = new StringBuilder();
        var state = new NextPlayerState(
                transitions,
                storage,
                new GameMessages(message -> {
                    sb.setLength(0);
                    sb.append(message);
                })
        );

        state.perform();
        assertThat(sb.toString(), is(String.format(PLAYER_MOVE_STR, first.getName())));
        state.perform();
        assertThat(sb.toString(), is(String.format(PLAYER_MOVE_STR, second.getName())));
        state.perform();
        assertThat(sb.toString(), is(String.format(PLAYER_MOVE_STR, first.getName())));
    }
}