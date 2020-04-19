package ru.job4j.tictactoe.state.impl;

import ru.job4j.tictactoe.view.View;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.messages.MessagePrinter;
import ru.job4j.tictactoe.player.provider.PlayerProvider;
import ru.job4j.tictactoe.state.State;
import ru.job4j.tictactoe.state.StateStorage;

import java.util.HashMap;
import java.util.Map;

public class MemoryStateStorage implements StateStorage {
    private final Map<String, State> stateMap = new HashMap<>();

    public MemoryStateStorage(MessagePrinter printer, int size, Logic logic, PlayerProvider provider, View view) {
        stateMap.put(StartState.class.getName(), new StartState(this, printer, size));
        stateMap.put(InitializeState.class.getName(), new InitializeState(this, logic, view));
        stateMap.put(PlayerMoveState.class.getName(), new PlayerMoveState(this, provider, printer, view));
        stateMap.put(CheckWinState.class.getName(), new CheckWinState(this, logic, printer, provider));
        stateMap.put(CheckMoveAvailableState.class.getName(), new CheckMoveAvailableState(this, printer, logic));
        stateMap.put(EndState.class.getName(), new EndState());
    }

    @Override
    public State get(String className) {
       return stateMap.get(className);
    }
}
