package ru.job4j.tictactoe;

import ru.job4j.tictactoe.board.impl.GameBoard;
import ru.job4j.tictactoe.cell.impl.MemoryCellStorage;
import ru.job4j.tictactoe.game.impl.TicTacToeGame;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.logic.impl.GameLogic;
import ru.job4j.tictactoe.message.MessagePrinter;
import ru.job4j.tictactoe.message.impl.GameMessagePrinter;
import ru.job4j.tictactoe.output.impl.ConsoleOutput;
import ru.job4j.tictactoe.player.impl.GamePlayer;
import ru.job4j.tictactoe.player.input.impl.BotRandomInput;
import ru.job4j.tictactoe.player.input.impl.HumanConsoleInput;
import ru.job4j.tictactoe.player.storage.PlayerStorage;
import ru.job4j.tictactoe.player.storage.impl.MemoryPlayerStorage;
import ru.job4j.tictactoe.policy.MultipleWinsPolicy;
import ru.job4j.tictactoe.policy.OneWinPolicy;
import ru.job4j.tictactoe.state.impl.MemoryStateStorage;
import ru.job4j.tictactoe.state.impl.StartState;
import ru.job4j.tictactoe.view.impl.ConsoleBoardView;

import java.util.Random;

import static ru.job4j.tictactoe.board.impl.GameBoard.DEFAULT_BOARD_SIZE;
import static ru.job4j.tictactoe.cell.Mark.O;
import static ru.job4j.tictactoe.cell.Mark.X;
import static ru.job4j.tictactoe.policy.MultipleWinsPolicy.DEFAULT_COUNT_TO_WIN;

public class TicTacToe {
    public static void main(String[] args) {
        var storage = new MemoryCellStorage();
        var board = new GameBoard(storage, DEFAULT_BOARD_SIZE);
        var printer = new GameMessagePrinter(new ConsoleOutput());
        var policy = new MultipleWinsPolicy(new OneWinPolicy(board, board), DEFAULT_COUNT_TO_WIN);
        var logic = new GameLogic(policy, board);

        PlayerStorage players = new MemoryPlayerStorage();
        players.add(new GamePlayer(O, "Dmitry", logic, new HumanConsoleInput(printer), printer));
        players.add(new GamePlayer(X, "Bot", logic, new BotRandomInput(board, new Random()), printer));

        var game = new TicTacToeGame();
        var stateStorage = new MemoryStateStorage(printer, board, logic, players, new ConsoleBoardView(board, board));
        game.setCurrentState(stateStorage.get(StartState.class.getName()));
        game.start();
    }
}
