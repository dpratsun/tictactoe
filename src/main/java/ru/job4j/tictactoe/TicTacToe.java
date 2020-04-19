package ru.job4j.tictactoe;

import ru.job4j.tictactoe.cell.CellStorage;
import ru.job4j.tictactoe.cell.impl.MemoryCellStorage;
import ru.job4j.tictactoe.view.impl.ConsoleBoardView;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.logic.impl.GameLogic;
import ru.job4j.tictactoe.loop.impl.Game;
import ru.job4j.tictactoe.messages.MessagePrinter;
import ru.job4j.tictactoe.messages.impl.GameMessagePrinter;
import ru.job4j.tictactoe.output.impl.ConsoleOutput;
import ru.job4j.tictactoe.player.impl.Bot;
import ru.job4j.tictactoe.player.impl.Human;
import ru.job4j.tictactoe.player.input.impl.ConsolePlayerInput;
import ru.job4j.tictactoe.player.provider.impl.CurrentPlayerProvider;
import ru.job4j.tictactoe.player.storage.MemoryPlayerStorage;
import ru.job4j.tictactoe.player.storage.PlayerStorage;
import ru.job4j.tictactoe.policy.MultipleWinsPolicy;
import ru.job4j.tictactoe.policy.OneWinPolicy;
import ru.job4j.tictactoe.state.impl.MemoryStateStorage;
import ru.job4j.tictactoe.state.impl.StartState;

import static ru.job4j.tictactoe.cell.Mark.O;
import static ru.job4j.tictactoe.cell.Mark.X;
import static ru.job4j.tictactoe.logic.impl.GameLogic.DEFAULT_BOARD_SIZE;
import static ru.job4j.tictactoe.policy.MultipleWinsPolicy.DEFAULT_COUNT_TO_WIN;

public class TicTacToe {
    public static void main(String[] args) {
        final CellStorage storage = new MemoryCellStorage();
        final MessagePrinter printer = new GameMessagePrinter(new ConsoleOutput());
        final Logic logic = new GameLogic(
                storage,
                new MultipleWinsPolicy(new OneWinPolicy(storage, DEFAULT_BOARD_SIZE), DEFAULT_COUNT_TO_WIN),
                DEFAULT_BOARD_SIZE
        );

        PlayerStorage players = new MemoryPlayerStorage();
        players.add(new Human(O, "Dmitry", logic, new ConsolePlayerInput(printer), printer));
        players.add(new Bot(X, "Bot", storage));

        var game = new Game();
        game.setNext(
                new MemoryStateStorage(
                        printer,
                        DEFAULT_BOARD_SIZE,
                        logic,
                        new CurrentPlayerProvider(players),
                        new ConsoleBoardView(DEFAULT_BOARD_SIZE, storage)).get(StartState.class.getName()));
        game.loop();
    }
}
