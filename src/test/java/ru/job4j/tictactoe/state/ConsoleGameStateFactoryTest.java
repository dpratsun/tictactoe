package ru.job4j.tictactoe.state;

import org.junit.Test;
import ru.job4j.tictactoe.logic.GameLogic;
import ru.job4j.tictactoe.messages.GameMessages;
import ru.job4j.tictactoe.output.ConsoleOutput;
import ru.job4j.tictactoe.player.storage.PlayerListStorage;
import ru.job4j.tictactoe.policy.OneWinPolicy;
import ru.job4j.tictactoe.table.GameDataTable;

import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.*;
import static ru.job4j.tictactoe.table.GameDataTable.DEFAULT_SIZE;

public class ConsoleGameStateFactoryTest {
    @Test
    public void whenInitialStateShouldReturnStateObject() {
        var data = new GameDataTable(DEFAULT_SIZE);
        var state = new ConsoleGameStateFactory(
                new PlayerListStorage(),
                data,
                new GameMessages(new ConsoleOutput()),
                new GameLogic(data, new OneWinPolicy(data))
        ).initialState();
        assertThat(state, isA(State.class));
    }
}