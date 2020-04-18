package ru.job4j.tictactoe;

import ru.job4j.tictactoe.game.GameLoop;
import ru.job4j.tictactoe.logic.GameLogic;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.messages.GameMessages;
import ru.job4j.tictactoe.messages.Messages;
import ru.job4j.tictactoe.output.ConsoleOutput;
import ru.job4j.tictactoe.player.Bot;
import ru.job4j.tictactoe.player.Human;
import ru.job4j.tictactoe.player.input.PlayerConsoleInput;
import ru.job4j.tictactoe.player.service.BotServiceImpl;
import ru.job4j.tictactoe.player.service.HumanServiceImpl;
import ru.job4j.tictactoe.player.storage.PlayerListStorage;
import ru.job4j.tictactoe.player.storage.PlayerStorage;
import ru.job4j.tictactoe.policy.MultipleWinsPolicy;
import ru.job4j.tictactoe.state.ConsoleGameStateFactory;
import ru.job4j.tictactoe.table.GameDataTable;
import ru.job4j.tictactoe.table.GameTable;

import static ru.job4j.tictactoe.policy.MultipleWinsPolicy.DEFAULT_COUNT_TO_WIN;
import static ru.job4j.tictactoe.table.GameDataTable.DEFAULT_SIZE;
import static ru.job4j.tictactoe.table.Mark.O;
import static ru.job4j.tictactoe.table.Mark.X;

public class TicTacToe {
    public static void main(String[] args) {
        final GameTable data = new GameDataTable(DEFAULT_SIZE);
        final Messages messages = new GameMessages(new ConsoleOutput());
        final Logic logic = new GameLogic(data, new MultipleWinsPolicy(data, DEFAULT_COUNT_TO_WIN));

        PlayerStorage players = new PlayerListStorage();
        players.add(new Human(O, "Dmitry", new HumanServiceImpl(new PlayerConsoleInput(), logic, messages)));
        players.add(new Bot(X, "Bot", new BotServiceImpl(data)));

        new GameLoop(new ConsoleGameStateFactory(players, data, messages, logic).initialState()).loop();
    }
}
