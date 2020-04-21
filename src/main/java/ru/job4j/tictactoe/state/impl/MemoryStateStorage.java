package ru.job4j.tictactoe.state.impl;

import ru.job4j.tictactoe.board.BoardSize;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.message.MessagePrinter;
import ru.job4j.tictactoe.player.storage.PlayerStorage;
import ru.job4j.tictactoe.player.storage.impl.MemoryCurrentPlayerStorage;
import ru.job4j.tictactoe.state.State;
import ru.job4j.tictactoe.state.StateStorage;
import ru.job4j.tictactoe.view.View;

import java.util.HashMap;
import java.util.Map;

public class MemoryStateStorage implements StateStorage {
    private final Map<String, State> stateMap = new HashMap<>();

    public MemoryStateStorage(MessagePrinter printer, BoardSize boardSize, Logic logic, PlayerStorage players, View view) {
        var currentPlayer = new MemoryCurrentPlayerStorage(players);
        stateMap.put(StartState.class.getName(), new StartState(this, printer, boardSize));
        stateMap.put(InitializeState.class.getName(), new InitializeState(this, logic, view, currentPlayer));
        stateMap.put(PlayerMoveState.class.getName(), new PlayerMoveState(this, currentPlayer, printer, view));
        stateMap.put(CheckWinState.class.getName(), new CheckWinState(this, logic, printer, currentPlayer));
        stateMap.put(CheckMoveAvailableState.class.getName(),
                new CheckMoveAvailableState(this, printer, logic, currentPlayer));
        stateMap.put(EndState.class.getName(), new EndState());
    }

    @Override
    public State get(String className) {
       return stateMap.get(className);
    }
}
