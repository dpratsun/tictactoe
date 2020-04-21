package ru.job4j.tictactoe.game;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.board.impl.GameBoard;
import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.cell.impl.MemoryCellStorage;
import ru.job4j.tictactoe.game.impl.TicTacToeGame;
import ru.job4j.tictactoe.logic.impl.GameLogic;
import ru.job4j.tictactoe.message.Message;
import ru.job4j.tictactoe.message.MessagePrinter;
import ru.job4j.tictactoe.player.impl.GamePlayer;
import ru.job4j.tictactoe.player.input.PlayerInput;
import ru.job4j.tictactoe.player.storage.impl.MemoryCurrentPlayerStorage;
import ru.job4j.tictactoe.player.storage.impl.MemoryPlayerStorage;

import ru.job4j.tictactoe.policy.MultipleWinsPolicy;
import ru.job4j.tictactoe.policy.OneWinPolicy;
import ru.job4j.tictactoe.state.impl.MemoryStateStorage;
import ru.job4j.tictactoe.state.impl.StartState;
import ru.job4j.tictactoe.view.View;

import static org.mockito.Mockito.*;
import static ru.job4j.tictactoe.board.impl.GameBoard.DEFAULT_BOARD_SIZE;
import static ru.job4j.tictactoe.cell.Mark.O;
import static ru.job4j.tictactoe.cell.Mark.X;
import static ru.job4j.tictactoe.policy.MultipleWinsPolicy.DEFAULT_COUNT_TO_WIN;


public class TicTacToeGameIntegrationTest {
    private TicTacToeGame game;
    private MessagePrinter printer;

    @Before
    public void setUp() {
        printer = mock(MessagePrinter.class);
        game = new TicTacToeGame();

        var cells = new MemoryCellStorage();
        var board = new GameBoard(cells, DEFAULT_BOARD_SIZE);
        var policy = new MultipleWinsPolicy(new OneWinPolicy(board, board), DEFAULT_COUNT_TO_WIN);
        var logic = new GameLogic(policy, board);
        var players = new MemoryPlayerStorage();

        var firstPlayerInput = mock(PlayerInput.class);
        when(firstPlayerInput.get()).thenReturn(new Cell(0, 0), new Cell(1, 1), new Cell(2, 2));

        var secondPlayerInput = mock(PlayerInput.class);
        when(secondPlayerInput.get()).thenReturn(new Cell(0, 1), new Cell(0, 2), new Cell(1, 0));

        players.add(new GamePlayer(O, "Dmitry", logic, firstPlayerInput, printer));
        players.add(new GamePlayer(X, "Alex", logic, secondPlayerInput, printer));

        var states = new MemoryStateStorage(printer, board, logic, players, mock(View.class));

        game.setCurrentState(states.get(StartState.class.getName()));
    }

    @Test
    public void whenPlayerWinsThanLastMessageShouldContainCongratulationsWithHisName() {
        game.start();

        verify(printer, atLeastOnce()).print(Message.PLAYER_WIN_MESSAGE, "Dmitry");
    }
}
