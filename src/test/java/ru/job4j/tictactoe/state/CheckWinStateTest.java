package ru.job4j.tictactoe.state;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.player.Player;
import ru.job4j.tictactoe.state.impl.CheckWinState;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.job4j.tictactoe.cell.Mark.X;

public class CheckWinStateTest {
//    private StringBuilder sb;
//    private Player player;
//    private State state;
//    private Logic logic;
//    private StateTransition transitions;
//
//    @Before
//    public void setUp() {
//        sb = new StringBuilder();
//        player = mock(Player.class);
//        logic = mock(Logic.class);
//        transitions = new StateTransitions();
//        transitions.add(KEY, () -> null);
//        state = new CheckWinState(
//                factory, transitions,
//                logic,
//                new GameMessages(message -> sb.append(message))
//        );
//    }
//
//    @Test
//    public void whenNotWinThanPerformShouldReturnNextState() {
//        when(logic.isWin(X)).thenReturn(false);
//        when(player.getMark()).thenReturn(X);
//
//        state.forPlayer(player);
//
//        assertThat(state.perform(), isA(State.class));
//        assertThat(sb.toString(), is(""));
//    }
//
//    @Test
//    public void whenWinThanPerformShouldReturnNull() {
//        when(logic.isWin(X)).thenReturn(true);
//        when(player.getMark()).thenReturn(X);
//        when(player.getName()).thenReturn("Dmitry");
//
//        state.forPlayer(player);
//
//        assertNull(state.perform());
//        assertThat(sb.toString(), is(String.format(GameMessages.PLAYER_WINNER_STR, "Dmitry")));
//    }
}