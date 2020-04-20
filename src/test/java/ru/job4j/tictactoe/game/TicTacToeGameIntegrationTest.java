package ru.job4j.tictactoe.game;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.cell.impl.MemoryCellStorage;
import ru.job4j.tictactoe.game.impl.TicTacToeGame;
import ru.job4j.tictactoe.logic.impl.GameLogic;
import ru.job4j.tictactoe.messages.MessagePrinter;
import ru.job4j.tictactoe.messages.Messages;
import ru.job4j.tictactoe.player.impl.GamePlayer;
import ru.job4j.tictactoe.player.input.PlayerInput;
import ru.job4j.tictactoe.player.provider.impl.CurrentPlayerProvider;
import ru.job4j.tictactoe.player.storage.MemoryPlayerStorage;
import ru.job4j.tictactoe.policy.impl.MultipleWinsPolicy;
import ru.job4j.tictactoe.policy.impl.OneWinPolicy;
import ru.job4j.tictactoe.state.impl.MemoryStateStorage;
import ru.job4j.tictactoe.state.impl.StartState;
import ru.job4j.tictactoe.view.View;

import static org.mockito.Mockito.*;
import static ru.job4j.tictactoe.cell.Mark.O;
import static ru.job4j.tictactoe.cell.Mark.X;
import static ru.job4j.tictactoe.logic.impl.GameLogic.DEFAULT_BOARD_SIZE;
import static ru.job4j.tictactoe.policy.impl.MultipleWinsPolicy.DEFAULT_COUNT_TO_WIN;

public class TicTacToeGameIntegrationTest {
    private TicTacToeGame game;
    private MessagePrinter printer;

    @Before
    public void setUp() {
        printer = mock(MessagePrinter.class);
        game = new TicTacToeGame();

        var cells = new MemoryCellStorage();
        var policy = new MultipleWinsPolicy(new OneWinPolicy(cells, DEFAULT_BOARD_SIZE), DEFAULT_COUNT_TO_WIN);
        var logic = new GameLogic(cells, policy, DEFAULT_BOARD_SIZE);
        var players = new MemoryPlayerStorage();

        var firstPlayerInput = mock(PlayerInput.class);
        when(firstPlayerInput.get()).thenReturn(new Cell(0, 0), new Cell(1, 1), new Cell(2, 2));

        var secondPlayerInput = mock(PlayerInput.class);
        when(secondPlayerInput.get()).thenReturn(new Cell(0, 1), new Cell(0, 2), new Cell(1, 0));

        players.add(new GamePlayer(O, "Dmitry", logic, firstPlayerInput, printer));
        players.add(new GamePlayer(X, "Alex", logic, secondPlayerInput, printer));

        var states = new MemoryStateStorage(
                printer, logic, new CurrentPlayerProvider(players), mock(View.class), DEFAULT_BOARD_SIZE
        );

        game.setNext(states.get(StartState.class.getName()));
    }

    @Test
    public void name() {
        game.start();

        verify(printer, atLeastOnce()).print(Messages.PLAYER_WIN_MESSAGE, "Dmitry");
    }
}
