package ru.job4j.tictactoe.state;

import org.junit.Test;
import ru.job4j.tictactoe.messages.GameMessages;
import ru.job4j.tictactoe.output.ConsoleOutput;
import ru.job4j.tictactoe.player.Bot;
import ru.job4j.tictactoe.player.service.BotServiceImpl;
import ru.job4j.tictactoe.table.GameDataTable;
import ru.job4j.tictactoe.table.Mark;

import static org.junit.Assert.*;
import static ru.job4j.tictactoe.state.PlayerMoveState.KEY;
import static ru.job4j.tictactoe.table.GameDataTable.DEFAULT_SIZE;
import static ru.job4j.tictactoe.table.Mark.Empty;

public class PlayerMoveStateTest {
    @Test
    public void whenPerformThanCountOfNotMarkedCellsShouldDecrease() {
        StateTransitions transitions = new StateTransitions();
        transitions.add(KEY, () -> null);
        var state = new PlayerMoveState(
            transitions,
            new GameMessages(new ConsoleOutput())
        );
        GameDataTable data = new GameDataTable(DEFAULT_SIZE);
        Bot bot = new Bot(Mark.X, "Bot", new BotServiceImpl(data));
        state.forPlayer(bot);

        int prevEmptySize = data.getCellsByMark(Empty).size();

        state.perform();

        int currEmptySize = data.getCellsByMark(Empty).size();

        assertTrue(currEmptySize < prevEmptySize);
    }
}