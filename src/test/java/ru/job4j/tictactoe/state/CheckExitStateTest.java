package ru.job4j.tictactoe.state;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.messages.GameMessages;
import ru.job4j.tictactoe.player.Player;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.job4j.tictactoe.messages.GameMessages.PLAYER_EXIT_STR;
import static ru.job4j.tictactoe.state.CheckExitState.KEY;

public class CheckExitStateTest {
    private StringBuilder sb;
    private Player player;
    private State state;
    private StateTransition transitions;

    @Before
    public void setUp() {
        sb = new StringBuilder();
        player = mock(Player.class);
        transitions = new StateTransitions();
        transitions.add(KEY, () -> null);
        state = new CheckExitState(
                transitions,
                new GameMessages(message -> sb.append(message))
        );
    }

    @Test
    public void whenNotExitThanPerformShouldReturnNextState() {
        when(player.isExitGame()).thenReturn(false);
        state.forPlayer(player);

        assertNotNull(state.perform());
        assertThat(sb.toString(), is(""));
    }

    @Test
    public void whenExitThanPerformShouldReturnNull() {
        when(player.isExitGame()).thenReturn(true);
        when(player.getName()).thenReturn("Dmitry");
        state.forPlayer(player);

        assertNull(state.perform());
        assertThat(sb.toString(), is(String.format(PLAYER_EXIT_STR, "Dmitry")));
    }
}