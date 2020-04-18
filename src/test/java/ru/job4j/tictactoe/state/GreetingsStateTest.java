package ru.job4j.tictactoe.state;

import org.junit.Test;
import ru.job4j.tictactoe.messages.GameMessages;
import ru.job4j.tictactoe.table.GameDataTable;
import ru.job4j.tictactoe.view.ConsoleGameView;

import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.assertThat;
import static ru.job4j.tictactoe.state.GreetingsState.KEY;
import static ru.job4j.tictactoe.table.GameDataTable.DEFAULT_SIZE;

public class GreetingsStateTest {
    @Test
    public void whenPerformShouldReturnNextState() {
        var transitions = new StateTransitions();
        transitions.add(KEY, () -> null);
        var state = new GreetingsState(
                transitions,
                new GameMessages(message -> {
                }),
                new ConsoleGameView(new GameDataTable(DEFAULT_SIZE)),
                DEFAULT_SIZE
        );
        assertThat(state.perform(), isA(State.class));
    }
}